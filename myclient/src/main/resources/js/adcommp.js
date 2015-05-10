(function(h, f, c) {
    var k = {
        pushAD: function(a) {
            a = c.merge(c.merge({},
            c.DEFAULT), a);
            c.render(a)
        },
        nextPush: function(a) {
            c.render(a)
        },
        render: function(a) {
            var b = a.baseurl + "/" + a.action,
            e = c.randomFunName(a.namepre),
            g = "z_" + e,
            d = a.target || "__X",
            k = (a._curridx || 0) + 1;
            if (!f.getElementById(d)) {
                var d = f.getElementById(a.namepre + "_holder"),
                l = f.createElement("div");
                l.id = g;
                l.className = "box_" + a.namepre;
                d.appendChild(l);
                d = g;
                a.target = g
            }
            b = c.appendUrl(b, "id", a.id);
            b = c.appendUrl(b, "c", e);
            b = c.appendUrl(b, "cnidx", k);
            b = c.appendUrl(b, "ext", a.ext);
            a._tmpCallBack = e;
            a._targetUrl = b;
            a._tmpZWID = g;
            a._curridx = k;
            a._loadSuccess = !1;
            h[e] = function(b, e, d) {
                switch (b) {
                case 0:
                    a._loadSuccess = !0;
                    c.invoke(e, d, a);
                    break;
                default:
                    a._loadSuccess = !1
                }
                c.rmCall(a._tmpCallBack);
                if (a.onLoad) a.onLoad(a)
            };
            c.LazyLoad().js(b,
            function(a) {
                c.rmLazyScript(a._targetUrl);
                if (a._loadSuccess) {
                    if (a.onLoadSuccess) a.onLoadSuccess(a)
                } else if (a.onLoadFailed) a.onLoadFailed(a)
            },
            a)
        },
        invoke: function(a, b, e) {
            if (c[a]) a = c[a],
            a.invoke.call(a, b, e);
            else {
                var g = e.basecdnurl + "/" + a + ".js";
                c.LazyLoad().js(g,
                function(a) {
                    c.rmLazyScript(a.channelUrl);
                    c[a.channel] && c.invoke(a.channel, a.data, a.option)
                },
                {
                    channel: a,
                    data: b,
                    option: e,
                    channelUrl: g
                })
            }
        },
        rmCall: function(a) {
            try {
                delete h[a]
            } catch(b) {
                h[a] = void 0
            }
        },
        rmLazyScript: function(a) {
            var b = f.getElementsByTagName("head")[0];
            if (b) {
                for (var b = b.getElementsByTagName("script"), e, c = 0; c < b.length; c++) {
                    var d = b[c];
                    if (d.className && "lazyload" == d.className && d.src && d.src == a) {
                        e = d;
                        break
                    }
                }
                e && (e.onerror = null, d.onload = d.onreadystatechange = null, d.parentNode.removeChild(d))
            }
        },
        format: function(a, b) {
            return a.replace(/\{\{([^}]+)\}\}/g,
            function(a, c) {
                return void 0 !== b[c] ? b[c] : ""
            })
        },
        randomFunName: function(a) {
            for (var b; b = a + "_" + Math.round(1E3 * ((new Date).getTime() + Math.random())), Object.prototype.hasOwnProperty.call(h, b););
            return b
        },
        appendUrl: function(a, b, c) {
            return a + (( - 1 == a.indexOf("?") ? "?": "&") + b + "=" + encodeURIComponent(c))
        },
        merge: function(a, b) {
            for (var c in b) a[c] = b[c];
            return a
        },
        getEncode: function() {
            return f.characterSet || f.charset || f.defaultCharset || ""
        },
        getReferrer: function() {
            var a = f.referrer;
            if (!a) try {
                a = h.opener.location.href
            } catch(b) {}
            return a || ""
        },
        getRequest: function() {
            var a = f.location,
            b;
            try {
                b = h.top.location
            } catch(c) {}
            return (a = a !== b ? f.referrer: a.href) || ""
        },
        inArray: function(a, b) {
            for (var c = 0,
            f = a.length; c < f; c++) if (a[c] === b) return c;
            return - 1
        }
    };
    k.merge(c, k)
})(window, document, window.Sellbuyads || {});