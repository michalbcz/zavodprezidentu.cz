<!DOCTYPE html>
<html lang="cs">
<head>

    <!-- start: Meta -->
    <meta charset="utf-8">
    <title><g:meta name="app.name"/></title>
    <meta name="description" content="Přehled transparentních účtů prezidenstkých kandidátů.">
    <meta name="author" content="Lukáš Marek, Michal Bernhard">
    <!-- end: Meta -->

    <!-- start: Mobile Specific -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- end: Mobile Specific -->

    <!-- start: CSS -->
    <link id="bootstrap-style" href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link id="base-style" href="css/style.css" rel="stylesheet">
    <link id="base-style-responsive" href="css/style-responsive.css" rel="stylesheet">

    <!--[if lt IE 7 ]>
	<link id="ie-style" href="css/style-ie.css" rel="stylesheet">
	<![endif]-->
    <!--[if IE 8 ]>
	<link id="ie-style" href="css/style-ie.css" rel="stylesheet">
	<![endif]-->

    <!-- end: CSS -->


    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

    <!-- start: Favicon -->
    <link rel="shortcut icon" href="img/favicon.ico">
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
            <a class="brand" href="index.html"><span class="hidden-phone"><g:meta name="app.name"/></span></a>

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
                    <li><a href="index.html"><i class="icon-home icon-white"></i><span class="hidden-tablet">Dashboard</span></a>
                    </li>
                    <li><a href="ui.html"><i class="icon-eye-open icon-white"></i><span class="hidden-tablet">UI Features</span>
                    </a></li>
                </ul>
            </div><!--/.well -->
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

            <div>
                <hr>
                <ul class="breadcrumb">
                    <li>
                        <a href="#">Home</a> <span class="divider">/</span>
                    </li>
                    <li>
                        <a href="#">Dashboard</a>
                    </li>
                </ul>
                <hr>
            </div>

            <div class="row-fluid">

                <div class="circleStats">

                    <div class="span2" onTablet="span4" onDesktop="span2">
                        <div class="circleStatsItem red">
                            <i class="fa-icon-thumbs-up"></i>
                            <span class="plus">+</span>
                            <span class="percent">%</span>
                            <input type="text" value="58" class="orangeCircle"/>
                        </div>

                        <div class="box-small-title">User satisfaction</div>
                    </div>

                    <div class="span2" onTablet="span4" onDesktop="span2">
                        <div class="circleStatsItem blue">
                            <i class="fa-icon-bullhorn"></i>
                            <span class="plus">+</span>
                            <span class="percent">%</span>
                            <input type="text" value="8" class="blueCircle"/>
                        </div>

                        <div class="box-small-title">Popularity</div>
                    </div>

                    <div class="span2" onTablet="span4" onDesktop="span2">
                        <div class="circleStatsItem yellow">
                            <i class="fa-icon-user"></i>
                            <span class="plus">+</span>
                            <span class="percent">%</span>
                            <input type="text" value="12" class="yellowCircle"/>
                        </div>

                        <div class="box-small-title">New users</div>
                    </div>

                    <div class="noMargin span2" onTablet="span4" onDesktop="span2">
                        <div class="circleStatsItem pink">
                            <i class="fa-icon-globe"></i>
                            <span class="plus">+</span>
                            <span class="percent">%</span>
                            <input type="text" value="23" class="pinkCircle"/>
                        </div>

                        <div class="box-small-title">Visits</div>
                    </div>

                    <div class="span2" onTablet="span4" onDesktop="span2">
                        <div class="circleStatsItem green">
                            <i class="fa-icon-bar-chart"></i>
                            <span class="plus">+</span>
                            <span class="percent">%</span>
                            <input type="text" value="34" class="greenCircle"/>
                        </div>

                        <div class="box-small-title">Income</div>
                    </div>

                    <div class="span2" onTablet="span4" onDesktop="span2">
                        <div class="circleStatsItem lightorange">
                            <i class="fa-icon-shopping-cart"></i>
                            <span class="plus">+</span>
                            <span class="percent">%</span>
                            <input type="text" value="42" class="lightOrangeCircle"/>
                        </div>

                        <div class="box-small-title">Sales</div>
                    </div>

                </div>
                <div class="row-fluid">

                    <div class="circleStats">

                        <div class="span2" onTablet="span4" onDesktop="span2">
                            <div class="circleStatsItem red">
                                <i class="fa-icon-thumbs-up"></i>
                                <span class="plus">+</span>
                                <span class="percent">%</span>
                                <input type="text" value="58" class="orangeCircle"/>
                            </div>

                            <div class="box-small-title">User satisfaction</div>
                        </div>

                        <div class="span2" onTablet="span4" onDesktop="span2">
                            <div class="circleStatsItem blue">
                                <i class="fa-icon-bullhorn"></i>
                                <span class="plus">+</span>
                                <span class="percent">%</span>
                                <input type="text" value="8" class="blueCircle"/>
                            </div>

                            <div class="box-small-title">Popularity</div>
                        </div>

                        <div class="span2" onTablet="span4" onDesktop="span2">
                            <div class="circleStatsItem yellow">
                                <i class="fa-icon-user"></i>
                                <span class="plus">+</span>
                                <span class="percent">%</span>
                                <input type="text" value="12" class="yellowCircle"/>
                            </div>

                            <div class="box-small-title">New users</div>
                        </div>

                        <div class="noMargin span2" onTablet="span4" onDesktop="span2">
                            <div class="circleStatsItem pink">
                                <i class="fa-icon-globe"></i>
                                <span class="plus">+</span>
                                <span class="percent">%</span>
                                <input type="text" value="23" class="pinkCircle"/>
                            </div>

                            <div class="box-small-title">Visits</div>
                        </div>

                        <div class="span2" onTablet="span4" onDesktop="span2">
                            <div class="circleStatsItem green">
                                <i class="fa-icon-bar-chart"></i>
                                <span class="plus">+</span>
                                <span class="percent">%</span>
                                <input type="text" value="34" class="greenCircle"/>
                            </div>

                            <div class="box-small-title">Income</div>
                        </div>

                        <div class="span2" onTablet="span4" onDesktop="span2">
                            <div class="circleStatsItem lightorange">
                                <i class="fa-icon-shopping-cart"></i>
                                <span class="plus">+</span>
                                <span class="percent">%</span>
                                <input type="text" value="42" class="lightOrangeCircle"/>
                            </div>

                            <div class="box-small-title">Sales</div>
                        </div>

                    </div>


                </div>

            <hr>


            <!-- end: Content -->
        </div><!--/#content.span10-->
    </div><!--/fluid-row-->


    <div class="clearfix"></div>

    <footer>
        <p>
            <span style="text-align:left;float:left">&copy; <a href="" target="_blank">@krtek_cz & @michalbcz</a> 2012</span>
            <span style="text-align:right;float:right">Powered by: <a href="#">Grails & Perfectum Dashboard</a></span>
        </p>

        <div class="clearfix"></div>
    </footer>

</div><!--/.fluid-container-->

<!-- start: JavaScript-->

<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/jquery-ui-1.8.21.custom.min.js"></script>

<script src="js/bootstrap.js"></script>

<script src="js/jquery.cookie.js"></script>

<script src='js/fullcalendar.min.js'></script>

<script src='js/jquery.dataTables.min.js'></script>

<script src="js/excanvas.js"></script>
<script src="js/jquery.flot.min.js"></script>
<script src="js/jquery.flot.pie.min.js"></script>
<script src="js/jquery.flot.stack.js"></script>
<script src="js/jquery.flot.resize.min.js"></script>

<script src="js/jquery.chosen.min.js"></script>

<script src="js/jquery.uniform.min.js"></script>

<script src="js/jquery.cleditor.min.js"></script>

<script src="js/jquery.noty.js"></script>

<script src="js/jquery.elfinder.min.js"></script>

<script src="js/jquery.raty.min.js"></script>

<script src="js/jquery.iphone.toggle.js"></script>

<script src="js/jquery.uploadify-3.1.min.js"></script>

<script src="js/jquery.gritter.min.js"></script>

<script src="js/jquery.imagesloaded.js"></script>

<script src="js/jquery.masonry.min.js"></script>

<script src="js/jquery.knob.js"></script>

<script src="js/jquery.sparkline.min.js"></script>

<script src="js/custom.js"></script>
<script type="text/javascript">

</script>
<!-- end: JavaScript-->

</body>
</html>
