(function(a, e, b) {
    b.LazyLoad().js("http://js.xtgreat.com/mz_ad_serving.js",
    function() {
        b.rmLazyScript("http://js.xtgreat.com/mz_ad_serving.js")
    });
    b.MZADX = {
        invoke: function(c, d) {
            a.__mz_rpq = a.__mz_rpq || [];
            a.__mz_rpq.push(b.merge({
                r: "1",
                _id: d.target,
                _srv: "MZADX",
                _baseurl: "http://s.x.cn.grpreach.com/ax",
                _callback: function(a, c) {
                    a || c || b.nextPush(d)
                }
            },
            c || {}))
        }
    }
})(window, document, window.Sellbuyads || {});