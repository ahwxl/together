(function(q, g) {
    q.Sellbuyads = {
        init: function(m) {
            var d = this;
            g.write("<div id='" + d.DEFAULT.namepre + "_holder'></div>");
            var n = d.DEFAULT.basecdnurl + '/base/' + d.DEFAULT.basecompname;
            d.LazyLoad().js(n,
            function(g) {
                d.rmLazyScript(g);
                for (var n in m) d.pushAD(m[n])
            },
            n)
        },
        pushAD: function(g) {},
        nextPush: function(g) {},
        LazyLoad: function() {
            function m(b, a) {
                var k = g.createElement(b),
                c;
                for (c in a) a.hasOwnProperty(c) && k.setAttribute(c, a[c]);
                return k
            }
            function d(b) {
                var a = r[b],
                c,
                d;
                a && (c = a.callback, d = a.urls, d.shift(), h = 0, d.length || (c && c.call(a.context, a.obj), r[b] = null, t[b].length && p(b)))
            }
            function n() {
                var b = navigator.userAgent;
                c = {
                    async: !0 === g.createElement('script').async
                }; (c.webkit = /AppleWebKit\//.test(b)) || (c.ie = /MSIE/.test(b)) || (c.opera = /Opera/.test(b)) || (c.gecko = /Gecko\//.test(b)) || (c.unknown = !0)
            }
            function p(b, a, k, h, p) {
                var s = function() {
                    d(b)
                },
                u = 'css' === b,
                v = [],
                e,
                l,
                f,
                w;
                c || n();
                if (a) if (a = 'string' === typeof a ? [a] : a.concat(), u || c.async || c.gecko || c.opera) t[b].push({
                    urls: a,
                    callback: k,
                    obj: h,
                    context: p
                });
                else for (e = 0, l = a.length; e < l; ++e) t[b].push({
                    urls: [a[e]],
                    callback: e === l - 1 ? k: null,
                    obj: h,
                    context: p
                });
                if (!r[b] && (w = r[b] = t[b].shift())) {
                    x || (x = g.head || g.getElementsByTagName('head')[0]);
                    a = w.urls;
                    e = 0;
                    for (l = a.length; e < l; ++e) k = a[e],
                    u ? f = c.gecko ? m('style') : m('link', {
                        href: k,
                        rel: 'stylesheet'
                    }) : (f = m('script', {
                        src: k
                    }), f.async = !0),
                    f.className = 'lazyload',
                    f.setAttribute('charset', 'utf-8'),
                    c.ie && !u ? f.onreadystatechange = function() { / loaded | complete / .test(f.readyState) && (f.onreadystatechange = null, s())
                    }: u && (c.gecko || c.webkit) ? c.webkit ? (w.urls[e] = f.href, y()) : (f.innerHTML = '@import "' + k + '";', q(f)) : f.onload = f.onerror = s,
                    v.push(f);
                    e = 0;
                    for (l = v.length; e < l; ++e) x.appendChild(v[e])
                }
            }
            function q(b) {
                var a;
                try {
                    a = !!b.sheet.cssRules
                } catch(c) {
                    h += 1;
                    200 > h ? setTimeout(function() {
                        q(b)
                    },
                    50) : a && d('css');
                    return
                }
                d('css')
            }
            function y() {
                var b = r.css,
                a;
                if (b) {
                    for (a = s.length; 0 <= --a;) if (s[a].href === b.urls[0]) {
                        d('css');
                        break
                    }
                    h += 1;
                    b && (200 > h ? setTimeout(y, 50) : d('css'))
                }
            }
            var c, x, r = {},
            h = 0,
            t = {
                css: [],
                js: []
            },
            s = g.styleSheets;
            return {
                css: function(b, a, c, d) {
                    p('css', b, a, c, d)
                },
                js: function(b, a, c, d) {
                    p('js', b, a, c, d)
                }
            }
        },
        DEFAULT: {
            basecdnurl: 'http://cdn.sellbuyads.cn',
            baseurl: 'http://track.sellbuyads.cn/APIV2',
            basecompname: 'adcomp.js',
            action: 'require',
            namepre: 'Sellbuyads'
        }
    };
    q.Sellbuyads.init([{
        "id": "cnd2_cnd3",
        "version": 7,
        "target": "AD_Sellbuyads",
        "ext": "7577|1387"
    }])
})(window, document);