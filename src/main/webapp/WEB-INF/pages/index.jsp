<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>SoftTour</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="css/settings.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">



</head>
<body bgcolor="#c0c0c0">
     <div class="wrapper container" style="margin-bottom: 15px;">
        <header>
            <div class="row" style="margin-top: 4px;">
                <div class="col-md-1"><img src="img/2.png" style="max-height: 100%; max-width: 100%;"></div><!--замінити на картинку малого розміру-->
                <div class="col-md-4"><h3 id="heading">SoftTour - подорожуй зручно!</h3></div>
                <div class="col-md-7"></div>
            </div>    
        </header>
        <div class="container" id="white_main">
            <nav class="navbar navbar-default" role="navigation" style="margin-top:5px;">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/">SoftTour</a>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="search">Пошук</a></li>
                            <li><a href="#">Готелі</a></li>
                            <li><a href="#">Про нас</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">Увійти</a></li>
                            <li><a href="#">Реєстрація</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
            <div class="row">
                <div class="col-md-3 imgfont"  style="margin-left:50px;" ><img src="img/egypt_logo.png"><br>Єгипет</div>
                <div class="col-md-1"></div>
                <div class="col-md-3 imgfont"><img src="img/greece_logo.png"><br>Греція</div>
                <div class="col-md-1"></div>
                <div class="col-md-3 imgfont"><img src="img/turkey_logo.png"><br>Туреччина</div>
                <div class="col-md-1"></div>
        </div>
    </div><!--блок "контент"-->
    </div>
    <footer></footer>
     <script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>