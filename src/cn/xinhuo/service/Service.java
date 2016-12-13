package cn.xinhuo.service;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import com.router.property.PropertiesFactory;

import cn.xinhuo.entity.Daikuanjilu;
import cn.xinhuo.entity.Dianmian;
import cn.xinhuo.entity.Dianmianliushui;
import cn.xinhuo.entity.MyClass;
import cn.xinhuo.entity.ResultBean;
import cn.xinhuo.entity.Yonghu;
import cn.xinhuo.entity.Zichanxinxi;

@Produces("application/json")
@Consumes("application/json")
@Path("/")
public class Service {

    /**
     * 创建头部的默认样式
     * 
     * @param wb
     * @return
     */
    @SuppressWarnings("deprecation")
    public CellStyle getDefautHeaderStyle(HSSFWorkbook wb) {
        CellStyle style = wb.createCellStyle();
        // 线条
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 创建一个居中格式
        // 背景
        // style.setFillBackgroundColor(new HSSFColor.BLUE_GREY().getIndex());
        // 字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("宋体");
        style.setFont(font);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /**
     * @param workBook
     * @param index
     *            第几个sheet
     * @return
     */
    public HSSFSheet createSheet(HSSFWorkbook workBook, int index, String sheetName) {
        HSSFSheet sheet = workBook.createSheet();
        sheet.setDefaultColumnWidth(25);
        workBook.setSheetName(index, sheetName);
        if (index == 1) {
            sheet.setSelected(true);// 将第一个sheet页设置为默认
        }
        // 创建header页
        // HSSFHeader header = sheet.getHeader();
        // header.setCenter("标题");
        sheet.setAutobreaks(true);
        return sheet;
    }

    /**
     * 下载每家店面的经营流水和明细情况
     * 
     * @param id
     * @param daikuanId
     * @param userId
     */
    @GET
    @Path("/downLoadFile")
    @Produces(MediaType.APPLICATION_OCTET_STREAM) //
    public void downLoadFile(@QueryParam("id") String id, @QueryParam("daikuanId") String daikuanId,
            @QueryParam("userId") String userId, @Context HttpServletRequest request,
            @Context HttpServletResponse response) {
        // 获取该店面所有的店面流水数据
        List<Dianmianliushui> allDianMianLiushui = getAllDianMianLiuShui(id);
        // 获取该店面的贷款记录
        List<Daikuanjilu> daikuanList = ServiceGate.getInstance().getHoutai().getDaikuanshenqinglist(-1, 1, id);
        // 获取该店面的个人信息
        Yonghu user = ServiceGate.getInstance().getHoutai().getYonghu(Integer.valueOf(userId));
        // 获取该店面的店面信息
        Dianmian shop = ServiceGate.getInstance().getHoutai().getDianmian(Integer.valueOf(userId));
        // 获取用户资产信息
        Zichanxinxi zichan = ServiceGate.getInstance().getHoutai().getZichan(Integer.valueOf(userId));
        // 创建一个Excel文件
        HSSFWorkbook workBook = new HSSFWorkbook();
        // 创建第一个sheet
        HSSFSheet firstSheet = createSheet(workBook, 0, "经营流水");
        // 创建第二个sheet
        HSSFSheet secondSheet = createSheet(workBook, 1, "明细");
        // 设置cell默认style
        CellStyle cellStyle = getDefautHeaderStyle(workBook);
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        HSSFRow firstSheetRow = firstSheet.createRow((int) 0);
        HSSFRow secondSheetRow = secondSheet.createRow((int) 0);
        HSSFCell firstSheetCell = firstSheetRow.createCell((short) 0);
        HSSFCell secondSheetCell = secondSheetRow.createCell((short) 0);
        // 经营流水sheet的header
        String[] firstSheetHeader = new String[] { "店面ID", "日期", "营业单数", "客流人数", "营业金额", "店面名称" };
        for (int i = 0; i < firstSheetHeader.length; i++) {
            firstSheetCell.setCellValue(firstSheetHeader[i]);
            firstSheetCell.setCellStyle(cellStyle);
            firstSheetCell = firstSheetRow.createCell((i + 1));
        }
        for (int i = 0; i < allDianMianLiushui.size(); i++) {
            firstSheetRow = firstSheet.createRow((i + 1));
            Dianmianliushui liushui = allDianMianLiushui.get(i);
            firstSheetRow.createCell(0).setCellValue(liushui.getDianmianId());
            firstSheetRow.createCell(1).setCellValue(liushui.getRiqi());
            firstSheetRow.createCell(2).setCellValue(liushui.getYinyedanshu());
            firstSheetRow.createCell(3).setCellValue(liushui.getKeliurenshu());
            firstSheetRow.createCell(4).setCellValue(liushui.getYinyejine());
            firstSheetRow.createCell(5).setCellValue(liushui.getDianmianmingchen());
        }
        /***************************************************************************************************************/
        // 明细sheet的header
        String[] secondSheetHeader = new String[] { "序号", "申请时间", "门店ID", "店面名称", "金额", "分期", "姓名", "手机号", "身份证号",
                "居住地址", "营业地址", "婚姻状况", "学历", "年销售", "月销售额", "客单价", "单月水电费", "食材占比(%)", "单月房租", "单月员工工资", "房", "车",
                "信用情况", "商铺" };
        for (int i = 0; i < secondSheetHeader.length; i++) {
            secondSheetCell.setCellValue(secondSheetHeader[i]);
            secondSheetCell.setCellStyle(cellStyle);
            secondSheetCell = secondSheetRow.createCell((i + 1));
        }
        // 贷款记录明细(项目一期贷款记录有且只会有一条)
        secondSheetRow = secondSheet.createRow(1);
        if (!CollectionUtils.isEmpty(daikuanList)) {
            Daikuanjilu daikuan = daikuanList.get(0);
            secondSheetRow.createCell(0).setCellValue(daikuan.getId());
            secondSheetRow.createCell(1).setCellValue(daikuan.getDaikuanshijian1());
            secondSheetRow.createCell(2).setCellValue(daikuan.getMendianId());
            secondSheetRow.createCell(3).setCellValue(daikuan.getDianmianmingchen());
            secondSheetRow.createCell(4).setCellValue(daikuan.getJine());
            secondSheetRow.createCell(5).setCellValue(daikuan.getFenqi());
        } else {
            secondSheetRow.createCell(0).setCellValue("");
            secondSheetRow.createCell(1).setCellValue("");
            secondSheetRow.createCell(2).setCellValue("");
            secondSheetRow.createCell(3).setCellValue("");
            secondSheetRow.createCell(4).setCellValue("");
            secondSheetRow.createCell(5).setCellValue("");
        }
        // 个人信息明细
        secondSheetRow.createCell(6).setCellValue(StringUtils.isNotBlank(user.getXingming()) ? user.getXingming() : "");
        secondSheetRow.createCell(7)
                .setCellValue(StringUtils.isNotBlank(user.getShoujihao()) ? user.getShoujihao() : "");
        secondSheetRow.createCell(8)
                .setCellValue(StringUtils.isNotBlank(user.getShengfenzhenghao()) ? user.getShengfenzhenghao() : "");
        secondSheetRow.createCell(9)
                .setCellValue(StringUtils.isNotBlank(user.getJuzhudizhi()) ? user.getJuzhudizhi() : "");
        secondSheetRow.createCell(10)
                .setCellValue(StringUtils.isNotBlank(user.getYingyedizhi()) ? user.getYingyedizhi() : "");
        secondSheetRow.createCell(11)
                .setCellValue(StringUtils.isNotBlank(user.getHunyinzhuangkuang()) ? user.getHunyinzhuangkuang() : "");
        secondSheetRow.createCell(12).setCellValue(StringUtils.isNotBlank(user.getXueli()) ? user.getXueli() : "");
        // 店面信息明细
        secondSheetRow.createCell(13)
                .setCellValue(StringUtils.isNotBlank(shop.getNianxiaoshoue()) ? shop.getNianxiaoshoue() : "");
        secondSheetRow.createCell(14)
                .setCellValue(StringUtils.isNotBlank(shop.getYuexiaoshoue()) ? shop.getYuexiaoshoue() : "");
        secondSheetRow.createCell(15)
                .setCellValue(StringUtils.isNotBlank(shop.getKedanjia()) ? shop.getKedanjia() : "");
        secondSheetRow.createCell(16)
                .setCellValue(StringUtils.isNotBlank(shop.getDanyueshuidianfei()) ? shop.getDanyueshuidianfei() : "");
        secondSheetRow.createCell(17)
                .setCellValue(StringUtils.isNotBlank(shop.getPercentage()) ? shop.getPercentage() : "");
        secondSheetRow.createCell(18)
                .setCellValue(StringUtils.isNotBlank(shop.getDanyuefangzufei()) ? shop.getDanyuefangzufei() : "");
        secondSheetRow.createCell(19).setCellValue(
                StringUtils.isNotBlank(shop.getDanyueyuangonggongzi()) ? shop.getDanyueyuangonggongzi() : "");
        // 资产信息明细
        secondSheetRow.createCell(20).setCellValue(StringUtils.isNotBlank(zichan.getFang()) ? zichan.getFang() : "");
        secondSheetRow.createCell(21).setCellValue(StringUtils.isNotBlank(zichan.getChe()) ? zichan.getChe() : "");
        secondSheetRow.createCell(22)
                .setCellValue(StringUtils.isNotBlank(zichan.getXinyongqingkuang()) ? zichan.getXinyongqingkuang() : "");
        secondSheetRow.createCell(23)
                .setCellValue(StringUtils.isNotBlank(zichan.getShangpu()) ? zichan.getShangpu() : "");
        ServletOutputStream out = null;
        try { // 如果文件名有中文，必须URL编码 String
              // fileName = URLEncoder.encode(fileName, "UTF-8"); fileName = newString(fileName.getBytes("gb2312"),
              // "ISO8859-1");
            String fileName = URLEncoder.encode(shop.getMingchen(), "UTF-8");
            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
            // Workbook workBook1 = ExcelUtil.defaultExport(false, allDianMianLiushui,define, "体检年度的排期");
            out = response.getOutputStream();
            workBook.write(out);
            out.close();
            out.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // 序号 申请时间 门店ID 店面名称 金额 分期 | 姓名 手机号 身份证号 居住地址 营业地址 婚姻状况 学历 | 年销售 月销售额 客单价 单月水电费 单月房租 单月员工工资 | 房 车 信用情况 商铺
        // FileOutputStream outputStream;
        // try {
        // outputStream = new FileOutputStream("C://Users//Administrator//Desktop//" + shop.getMingchen() + ".xls");
        // workBook.write(outputStream);
        // outputStream.flush();
        // outputStream.close();
        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }
        System.out.println("文件下载done!");
    }

    /**
     * 获取该店面所有的店面流水
     * 
     * @param dianmianId
     * @return
     */
    private List<Dianmianliushui> getAllDianMianLiuShui(String dianmianId) {
        // 获取店面流水次数
        int totalCount = ServiceGate.getInstance().getHoutai().getDianmianliushuicishu(dianmianId);
        // 获取店面流水总页数
        int totalPage = (totalCount % 20) > 0 ? (totalCount / 20) + 1 : totalCount / 20;
        // 获取该店面所有的店面流水数据
        List<Dianmianliushui> allDianMianLiushui = new LinkedList<Dianmianliushui>();
        // 如果店面流水次数超过20条，则循环获取所有的店面流水
        if (totalPage >= 1) {
            for (int i = 1; i <= totalPage; i++) {
                List<Dianmianliushui> childList = ServiceGate.getInstance().getHoutai().getDianmianliushui(dianmianId,
                        i);
                allDianMianLiushui.addAll(childList);
            }
        }
        return allDianMianLiushui;
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/getDaikuanshenqing/")
    public Object getDaikuanshenqing(@QueryParam("style") int style, @QueryParam("pageNo") int pageNo) {
        return ServiceGate.getInstance().getHoutai().getDaikuanshenqinglist(style, pageNo);
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/getDaikuanshenqingByperson/")
    public Object getDaikuanshenqingByperson(@QueryParam("style") int style, @QueryParam("pageNo") int pageNo,
            @QueryParam("dianmianid") String dianmianid) {
        return ServiceGate.getInstance().getHoutai().getDaikuanshenqinglist(style, pageNo, dianmianid);
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/getliushui/")
    public Object getliushui(@QueryParam("preid") int preid) {
        return ServiceGate.getInstance().getHoutai().getliushui(preid);
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/savepre/")
    public Object savepre(@QueryParam("saas") int saasid, @QueryParam("dianmianname") String dmname,
            @QueryParam("dianmianid") String dmid, @QueryParam("saasmc") String saasmc, @QueryParam("man") String man,
            @QueryParam("phone") String phone, @QueryParam("jine") Integer jine) {
        String s = YingyeClient.getInstance().updateStatus(dmid, "1", jine);
        if (s.equals("success")) {
            ServiceGate.getInstance().getHoutai().savepre(dmid, dmname, saasid, saasmc, new Date(), man, phone, jine);
            return new ResultBean("y");
        } else {
            return new ResultBean("n");
        }
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/getFangwen/")
    public Object getFangwen(@QueryParam("pageNo") int pageNo) {
        return ServiceGate.getInstance().getHoutai().getFangwenjilulist(pageNo);
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/getPre/")
    public Object getPre(@QueryParam("pageNo") int pageNo) {
        return ServiceGate.getInstance().getHoutai().getPre(pageNo);
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/getFangwencishu/")
    public Object getFangwencishu() {
        return ServiceGate.getInstance().getHoutai().getFangwenjilucishu();
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/getPrecishu/")
    public Object getPrecishu() {
        return ServiceGate.getInstance().getHoutai().getPrecishu();
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/getDaikuancishu/")
    public Object getDaikuancishu() {
        return ServiceGate.getInstance().getHoutai().getDaikuancishu();
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/dianmianliushui/")
    public Object getDianmianliushui(@QueryParam("dianmianId") String dianmianId, @QueryParam("pageNo") int pageNo) {
        return ServiceGate.getInstance().getHoutai().getDianmianliushui(dianmianId, pageNo);
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/dianmianliushuicishu/")
    public Object getDianmianliushuicishu(@QueryParam("dianmianId") String dianmianId) {
        return ServiceGate.getInstance().getHoutai().getDianmianliushuicishu(dianmianId);
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/fade/")
    public Object fade(@QueryParam("entityId") String dianmianId, @QueryParam("date") String date) {
        List<Dianmianliushui> l = new ArrayList<Dianmianliushui>();
        for (int i = 1; i < 31; i++) {
            Dianmianliushui d = new Dianmianliushui();
            d.setDianmianId(dianmianId);
            d.setDianmianmingchen("fade");
            d.setKeliurenshu("100");
            d.setYinyedanshu("100");
            d.setYinyejine("100");
            d.setRiqi(date + "-" + i);
            l.add(d);
        }
        return l;
    }

    @SuppressWarnings("unchecked")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/testPost/")
    public Object testPost(InputStream in) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) objectMapper.readValue(in,
                    Object.class);
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            MyClass o = (MyClass) context.getBean((String) map.get("name"));
            Set<String> set = map.keySet();
            for (Iterator<String> it = set.iterator(); it.hasNext();) {
                String key = it.next();
                if (!key.equals("name")) {
                    Field f = o.getClass().getDeclaredField(key);
                    f.setAccessible(true);
                    f.set(o, map.get(key));
                }
            }
            return o.exe();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/dianmianxiangqing/")
    public Object getDianmianxiangqing(@QueryParam("yonghuId") int yonghuid) {
        return ServiceGate.getInstance().getHoutai().getDianmian(yonghuid);
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/kehuxiangqing/")
    public Object getKehuxiangqing(@QueryParam("kehuId") int kehuId) {
        return ServiceGate.getInstance().getHoutai().getYonghu(kehuId);
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/kehuzichan/")
    public Object getKehuzichan(@QueryParam("kehuId") int kehuId) {
        return ServiceGate.getInstance().getHoutai().getZichan(kehuId);
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/yndaikuan/")
    public Object yndaikuan(@QueryParam("saas") int saasid, @QueryParam("name") String name) {
        int i = ServiceGate.getInstance().getHoutai().ifdaikuan(saasid, name);
        System.out.println(i);
        ResultBean r = new ResultBean();
        r.setResult(i + "");
        return r;
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/getSession/")
    public Object getSession(@QueryParam("usr") String usr, @QueryParam("pass") String pass) {
        if (usr.equals(PropertiesFactory.getInstance().getValue("ADMINACCOUNT"))
                && pass.equals(PropertiesFactory.getInstance().getValue("ADMINPASSWORD")))
            return "1122";
        else
            return "fail";
    }

    public boolean checkSession(@QueryParam("session") String session) {
        if (session == null) {
            return false;
        }
        if (session.equals("1122")) {
            return true;
        } else {
            return false;
        }
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/fangwen/")
    public Object fangwen(@QueryParam("saas") int saasid, @QueryParam("dianmianname") String dmname,
            @QueryParam("dianmianid") String dmid, @QueryParam("saasmc") String saasmc) {
        ServiceGate.getInstance().getHoutai().jilufangwen(dmid, dmname, saasid, saasmc, new Date());
        return new ResultBean("yes");
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/updateStatus/")
    public Object updateStatus(@QueryParam("status") int status, @QueryParam("id") int id, @QueryParam("fee") int fee,
            @QueryParam("daikuanid") int daikuanid) {
        String mendianid = (String.valueOf(id)).substring(1);
        System.out.println(mendianid);
        String r = ServiceGate.getInstance().getHoutai().updateStatus(id, mendianid, status, fee + "", daikuanid);
        return new ResultBean(r);
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/setSaaS/")
    public Object setSaaS(@QueryParam("jiekou") String jiekou, @QueryParam("name") String name) {
        try {
            ServiceGate.getInstance().getHoutai().SaaSdengji(jiekou, name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/submitDaikuanshenqing/")
    public boolean submitDaikuanshenqing(
            @QueryParam("jine") String jine, 
            @QueryParam("fenqi") String fenqi,
            @QueryParam("xinming") String xinming, 
            @QueryParam("shengfeng") String shengfeng,
            @QueryParam("juzhudizhi") String juzhudizhi, @QueryParam("yingyedizhi") String yingyedizhi,
            @QueryParam("phone") String phone, 
            @QueryParam("hunyin") String hunyin, 
            @QueryParam("xueli") String xueli,
            @QueryParam("nianxiaoshou") String nianxiaoshou, 
            @QueryParam("yuexiaoshou") String yuexiaoshou,
            @QueryParam("kedan") String kedan, 
            @QueryParam("danyueshuidian") String danyueshuidian,
            @QueryParam("danyueyuangong") String danyueyuangong, 
            @QueryParam("danyuefangzu") String danyuefangzu,
            @QueryParam("percentage") String percentage,
 @QueryParam("openYear") String openYear,
            @QueryParam("openMonth") String openMonth,
            @QueryParam("yingyezhizhao") String yingyezhizhao, 
            @QueryParam("zufanghetong") String zufanghetong,
            @QueryParam("fang") String fang, 
            @QueryParam("che") String che, 
            @QueryParam("shangpu") String shangpu,
            @QueryParam("xinyongqingkuang") String xinyongqingkung, 
            @QueryParam("dianpuname") String dianpu,
            @QueryParam("dianpuid") String dianpuid, 
            @QueryParam("saasid") int saasid,
            @QueryParam("saasmc") String saasmc) {
        Yonghu yonghu = new Yonghu();
        yonghu.setSaaSId(new Integer(saasid));
        yonghu.setHunyinzhuangkuang(hunyin);
        yonghu.setJuzhudizhi(juzhudizhi);
        yonghu.setYingyedizhi(yingyedizhi);
        yonghu.setShengfenzhenghao(shengfeng);
        yonghu.setShoujihao(phone);
        yonghu.setXingming(xinming);
        yonghu.setXueli(xueli);
        Dianmian dianmian = new Dianmian();
        dianmian.setDanyuefangzufei(danyuefangzu);
        dianmian.setDanyueshuidianfei(danyueshuidian);
        dianmian.setDanyueyuangonggongzi(danyueyuangong);
        dianmian.setPercentage(percentage);
        dianmian.setOpenYear(openYear);
        dianmian.setOpenMonth(openMonth);
        dianmian.setFangzuhetong("");
        dianmian.setKedanjia(kedan);
        dianmian.setMendianId(saasid + dianpuid);
        dianmian.setMingchen(dianpu);
        dianmian.setNianxiaoshoue(nianxiaoshou);
        dianmian.setSaaSId(new Integer(saasid));
        dianmian.setSaaSWaibuId(dianpu);
        dianmian.setYingyezhizhao("");
        dianmian.setYuexiaoshoue(yuexiaoshou);
        dianmian.setZuijinliushuishijian(0);
        Zichanxinxi zichan = new Zichanxinxi();
        zichan.setChe(che);
        zichan.setFang(fang);
        zichan.setShangpu(shangpu);
        zichan.setXinyongqingkuang(xinyongqingkung);
        try {
            ServiceGate.getInstance().getHoutai().shenqingdaikuan(yonghu, dianmian, zichan, new Date(), fenqi, jine,
                    saasmc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
