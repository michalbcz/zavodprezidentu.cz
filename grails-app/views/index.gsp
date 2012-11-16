<%@ page import="cz.zavodprezidentu.utils.Utils; cz.zavodprezidentu.utils.Consts; cz.zavodprezidentu.utils.Colorer" %>
<!DOCTYPE html>
<html lang="cs">
<head>

    <!-- start: Meta -->
    <meta charset="utf-8">
    <title><g:meta name="app.displayName"/></title>
    <meta name="description" content="Přehled transparentních účtů prezidenstkých kandidátů.">
    <meta name="author" content="Lukáš Marek, Michal Bernhard">
    <!-- end: Meta -->

    <!-- start: Mobile Specific -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- end: Mobile Specific -->

    <!-- start: CSS -->
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.1/css/bootstrap-combined.min.css" rel="stylesheet">
    <link id="base-style" href="${resource(dir: 'css', file: 'style.css')}" rel="stylesheet">
    <link id="base-style-responsive" href="${resource(dir: 'css', file: 'style-responsive.css')}" rel="stylesheet">
    <link id="override-style" href="${resource(dir: 'css', file: 'override.css')}" rel="stylesheet">

    <!--[if lt IE 7 ]>
	<link id="ie-style" href="${resource(dir: 'css', file: 'style-ie.css')}" rel="stylesheet">
	<![endif]-->
    <!--[if IE 8 ]>
	<link id="ie-style" href="${resource(dir: 'css', file: 'style-ie.css')}" rel="stylesheet">
	<![endif]-->

    <!-- end: CSS -->


    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js')}"></script>
	<![endif]-->

    <!-- start: Favicon -->
    <link rel="shortcut icon" href="${resource(dir: 'img', file: 'favicon.ico')}">
    <!-- end: Favicon -->

</head>

<body>
<div id="overlay">
    <ul>
        <li class="li1"></li>
        <li class="li2"></li>
        <li class="li3"></li>
        <li class="li4"></li>
        <li class="li5"></li>
        <li class="li6"></li>
    </ul>
</div>
<!-- start: Header -->

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="index.html"><img src="${resource(dir: 'images', file: 'logo.png')}"/><span class="hidden-phone">Závod prezidentů</span></a>

        </div>
    </div>
</div>
<!-- start: Header -->

<div class="container-fluid">
    <div class="row-fluid">

        <!-- start: Main Menu -->
        <div class="span2 main-menu-span">
            <div class="nav-collapse sidebar-nav">
                <ul class="nav nav-tabs nav-stacked main-menu">
                    <li><g:link controller='index' action='balance'><i class="icon-home icon-white"></i><span class="hidden-tablet">Zůstatky</span></g:link></li>
                    <li><g:link controller='index' action='income'><i class="icon-plus-sign icon-white"></i><span class="hidden-tablet">Příjmy</span></g:link></li>
                    <li><g:link controller='index' action='expense'><i class="icon-minus-sign icon-white"></i><span class="hidden-tablet">Výdaje</span></g:link></li>
                    <li><g:link controller='index' action='transactions'><i class="icon-comment icon-white"></i><span class="hidden-tablet">Příspěvky</span></g:link></li>
                </ul>

                <div id="social" class="hidden-tablet">
                    <div class="g-plusone"></div><br/><br/>
                    <div class="fb-like" data-href="http://www.zavodprezidentu.cz/" data-send="false" data-width="180" data-show-faces="false"></div></br>
                    <a href="https://twitter.com/intent/tweet?button_hashtag=zavodprezidentu" class="twitter-hashtag-button" data-lang="cs" data-url="http://zavodprezidentu.cz">Tweet #zavodprezidentu</a>
                    <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script><br/><br/>
                    <iframe src="http://ghbtns.com/github-btn.html?user=michalbcz&repo=zavodprezidentu.cz&type=fork"
                            allowtransparency="true" frameborder="0" scrolling="0" width="62" height="20"></iframe>
                </div>
            </div><!--.well -->
        </div><!--/span-->
    <!-- end: Main Menu -->

        <noscript>
            <div class="alert alert-block span10">
                <h4 class="alert-heading">Javascript</h4>

                <p>Prosím, povolte si <a href="http://cs.wikipedia.org/wiki/JavaScript"
                                         target="_blank">JavaScript</a>, jinak tu nic neuvidíte.</p>
            </div>
        </noscript>


        <div id="content" class="span10">
            <!-- start: Content -->

            <div class="row-fluid">
                <div class="box">
                    <div class="box-content">
                        <h1>${title}</h1>
                    </div>
                </div>
                <% def colorer = new Colorer() %>
                <div class="circleStats">
                    <g:each in="${accounts}" var="account">
                        <% def nextColor = colorer.nextByLogoColours() %>
                        <div class="span2 budik" onTablet="span4" onDesktop="span2">
                            <g:if test="${account.candidate.accountUrl}">
                                <div class="circleStatsItem ${nextColor.color}" style="background-image: url(${resource(dir: 'images/kandidati', file: account.candidate.image)});">
                            </g:if>
                            <g:else>
                                <div class="circleStatsItem ${nextColor.color} no-data" style="background-image: url(${resource(dir: 'images/kandidati', file: account.candidate.image)});">
                            </g:else>

                                <!-- <i class="fa-icon-user"></i> -->
<!--                                <span class="plus">+</span>
                                <span class="percent">%</span>
-->
                                <input type="text" value="${Utils.getPercentage(Math.abs(account[key]), Math.abs(max))}" class="${nextColor.circleColor}"/>
                            </div>
                            <div class="box-header">
                                <h2>${account.candidate.name}</h2>
                                ${format.format(account[key]).replaceAll(" ", "&nbsp;")}
                            </div>

                        </div>
                    </g:each>
                </div>
            </div>

            <hr>

            <div class="clearfix"></div>

            <footer>
                <p>
                    <span style="text-align:left;float:left">&copy; <a href="http://twitter.com/krtek_cz">@krtek_cz</a> &
                        <a href="http://twitter.com/michalb_cz">@michalbcz</a> 2012
                    </span>
                    <span style="text-align:right;float:right">Powered by: <a href="http://grails.org">Grails</a> &
                        <a href="https://wrapbootstrap.com/theme/perfectum-dashboard-admin-template-WB0PHMG9K">Perfectum Dashboard</a></span>
                </p>

                <div class="clearfix"></div>
            </footer>
            </div>
        </div><!--/.fluid-container-->
    </div>
</div>


<!-- start: JavaScript-->

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>

<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.1/js/bootstrap.min.js"></script>

<script src="${resource(dir: 'js', file: 'jquery.cookie.js')}"></script>

<script src="${resource(dir: 'js', file: 'fullcalendar.min.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.dataTables.min.js')}"></script>

<script src="${resource(dir: 'js', file: 'excanvas.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.chosen.min.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.uniform.min.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.cleditor.min.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.noty.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.elfinder.min.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.raty.min.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.uploadify-3.1.min.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.gritter.min.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.imagesloaded.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.masonry.min.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.knob.js')}"></script>

<script src="${resource(dir: 'js', file: 'jquery.sparkline.min.js')}"></script>

<script src="${resource(dir: 'js', file: 'custom.js')}"></script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36387794-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

<script type="text/javascript">
    (function() {
        window.___gcfg = {lang: 'cs'};

        var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
        po.src = 'https://apis.google.com/js/plusone.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
    })();
</script>

<div id="fb-root"></div>
<script>(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/cs_CZ/all.js#xfbml=1";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

</body>
</html>
