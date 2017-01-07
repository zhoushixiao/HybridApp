;
(function($, window, document, undefined) {

	/** 创建命名空间 */
	window.$ && (window.$.webapp = {});

	/*APIs*/
	$.webapp.Api = {};

	/** 内宽度 */
	$.webapp.getInnerWidth = function() {
		var w = window.innerWidth;
		return w < 240 ? 240 : w;
	};

	/** 内高度 */
	$.webapp.getInnerHeight = function() {
		var h = window.innerHeight;
		return h < 400 ? 400 : h;
	};

	/********************** 公共工具类 ***************/
	$.webapp.PublicUtil = {
		isNotEmpty: function(val) {
			return !this.isEmpty(val);
		},
		isEmpty: function(val) {
			if((val == null || typeof(val) == "undefined") || (typeof(val) == "string" && val == "" && val != "undefined")) {
				return true;
			} else {
				return false;
			}
		}
	}
})(jQuery, window, document);