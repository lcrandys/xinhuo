//$.fn是指jquery的命名空间，加上fn上的方法及属性，会对jquery实例每一个有效。 给jquery对象增加方法
jQuery.fn.pagination = function(maxentries, opts){
	opts = jQuery.extend({
		items_per_page:20,
		num_display_entries:10,
		current_page:0,
		num_edge_entries:0,
		link_to:"#",
		prev_text:"上一页",
		next_text:"下一页",
		ellipse_text:"...",
		prev_show_always:true,
		next_show_always:true,
		callback:function(){return false;}
	},opts||{});
	
	return this.each(function() {
		/**
		 * 璁＄畻鏈�ぇ鍒嗛〉鏄剧ず鏁扮洰
		 */
		function numPages() {
			return Math.ceil(maxentries/opts.items_per_page);
		}	
		/**
		 * 鏋佺鍒嗛〉鐨勮捣濮嬪拰缁撴潫鐐癸紝杩欏彇鍐充簬current_page 鍜�num_display_entries.
		 * @杩斿洖 {鏁扮粍(Array)}
		 */
		function getInterval()  {
			var ne_half = Math.ceil(opts.num_display_entries/2);
			var np = numPages();
			var upper_limit = np-opts.num_display_entries;
			var start = current_page>ne_half?Math.max(Math.min(current_page-ne_half, upper_limit), 0):0;
			var end = current_page>ne_half?Math.min(current_page+ne_half, np):Math.min(opts.num_display_entries, np);
			return [start,end];
		}
		
		/**
		 * 鍒嗛〉閾炬帴浜嬩欢澶勭悊鍑芥暟
		 * @鍙傛暟 {int} page_id 涓烘柊椤电爜
		 */
		function pageSelected(page_id, evt){
			current_page = page_id;
			drawLinks();
			var continuePropagation = opts.callback(page_id, panel);
			if (!continuePropagation) {
				if (evt.stopPropagation) {
					evt.stopPropagation();
				}
				else {
					evt.cancelBubble = true;
				}
			}
			return continuePropagation;
		}
		
		/**
		 * 姝ゅ嚱鏁板皢鍒嗛〉閾炬帴鎻掑叆鍒板鍣ㄥ厓绱犱腑
		 */
		function drawLinks() {
			panel.empty();
			var interval = getInterval();
			var np = numPages();
			// 杩欎釜杈呭姪鍑芥暟杩斿洖涓�釜澶勭悊鍑芥暟璋冪敤鏈夌潃姝ｇ‘page_id鐨刾ageSelected銆�
			var getClickHandler = function(page_id) {
				return function(evt){ return pageSelected(page_id,evt); }
			}
			//杈呭姪鍑芥暟鐢ㄦ潵浜х敓涓�釜鍗曢摼鎺�濡傛灉涓嶆槸褰撳墠椤靛垯浜х敓span鏍囩)
			var appendItem = function(page_id, appendopts){
				page_id = page_id<0?0:(page_id<np?page_id:np-1); // 瑙勮寖page id鍊�
				appendopts = jQuery.extend({text:page_id+1, classes:""}, appendopts||{});
				if(page_id == current_page){
					var lnk = jQuery("<span class='current'>"+(appendopts.text)+"</span>");
				}else{
					var lnk = jQuery("<a>"+(appendopts.text)+"</a>")
						.bind("click", getClickHandler(page_id))
						.attr('href', opts.link_to.replace(/__id__/,page_id));		
				}
				if(appendopts.classes){lnk.addClass(appendopts.classes);}
				panel.append(lnk);
			}
			// 浜х敓"Previous"-閾炬帴
			if(opts.prev_text && (current_page > 0 || opts.prev_show_always)){
				appendItem(current_page-1,{text:opts.prev_text, classes:"prev"});
			}
			// 浜х敓璧峰鐐�
			if (interval[0] > 0 && opts.num_edge_entries > 0)
			{
				var end = Math.min(opts.num_edge_entries, interval[0]);
				for(var i=0; i<end; i++) {
					appendItem(i);
				}
				if(opts.num_edge_entries < interval[0] && opts.ellipse_text)
				{
					jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panel);
				}
			}
			// 浜х敓鍐呴儴鐨勪簺閾炬帴
			for(var i=interval[0]; i<interval[1]; i++) {
				appendItem(i);
			}
			// 浜х敓缁撴潫鐐�
			if (interval[1] < np && opts.num_edge_entries > 0)
			{
				if(np-opts.num_edge_entries > interval[1]&& opts.ellipse_text)
				{
					jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panel);
				}
				var begin = Math.max(np-opts.num_edge_entries, interval[1]);
				for(var i=begin; i<np; i++) {
					appendItem(i);
				}
				
			}
			// 浜х敓 "Next"-閾炬帴
			if(opts.next_text && (current_page < np-1 || opts.next_show_always)){
				appendItem(current_page+1,{text:opts.next_text, classes:"next"});
			}
		}
		
		//浠庨�椤逛腑鎻愬彇current_page
		var current_page = opts.current_page;
		//鍒涘缓涓�釜鏄剧ず鏉℃暟鍜屾瘡椤垫樉绀烘潯鏁板�
		maxentries = (!maxentries || maxentries < 0)?1:maxentries;
		opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
		//瀛樺偍DOM鍏冪礌锛屼互鏂逛究浠庢墍鏈夌殑鍐呴儴缁撴瀯涓幏鍙�
		var panel = jQuery(this);
		// 鑾峰緱闄勫姞鍔熻兘鐨勫厓绱�
		this.selectPage = function(page_id){ pageSelected(page_id);}
		this.prevPage = function(){ 
			if (current_page > 0) {
				pageSelected(current_page - 1);
				return true;
			}
			else {
				return false;
			}
		}
		this.nextPage = function(){ 
			if(current_page < numPages()-1) {
				pageSelected(current_page+1);
				return true;
			}
			else {
				return false;
			}
		}
		// 鎵�湁鍒濆鍖栧畬鎴愶紝缁樺埗閾炬帴
		drawLinks();
        // 鍥炶皟鍑芥暟
        opts.callback(current_page, this);
	});
}


