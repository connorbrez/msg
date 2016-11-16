<html lang="en">
	<head>
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="keywords" content="msg">
		<meta name="author" content="Connor Brezinsky">

		<title>msg.</title>

		<!-- Bootstrap.min css -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
		<!-- Style css -->
		<link rel="stylesheet" href="css/style.css">
		<!-- FontAwesome -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

		<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">


	</head>
	<body>
		<nav class="navbar navbar-full navbar-light bg-faded">
			<ul class="nav navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#">about</a></li>
				<li class="nav-item"><a class="nav-link" href="#">download</a></li>
				<span class="navbar-text float-xs-right">
					<button type="button" class="btn btn-outline-secondary">Sign In</button>
				</span>
			</ul>
		</nav>
		<div class="container">
			<div class="header">
				<h1 id="title">msg.</h1>
				<h3 id="subtitle">Encrpyted messaging. For everyone.</h3>
				<button type="button" href="getstarted.jsp" class="btn btn-outline-secondary" style="margin-right: 20px; margin-top: 10px;">Get Started</button>
			</div>
		</div>
	
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" integrity="sha384-3ceskX3iaEnIogmQchP8opvBy3Mi7Ce34nWjpBIwVTHfGYWQS9jwHDVRnpKKHJg7" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.3.7/js/tether.min.js" integrity="sha384-XTs3FgkjiBgo8qjEjBk0tGmf3wPrWtA6coPfQDfFEY8AnYJwjalXCiosYRBIBZX8" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js" integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.19.0/TweenLite.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.19.0/plugins/CSSPlugin.min.js"></script>
	
	
		<script>
			window.onload = function() {
				//
				var title = document.getElementById("title");
				var subtitle = document.getElementById("subtitle");
	
				TweenLite.to(title, 2.5, {
					css : {
						opacity : "1",
						fontSize : "115px"
					}
				});
				
				TweenLite.to(subtitle, 2.5, {
					css : {
						opacity : "1"
					}
				});
	
			}
		</script>
	
	</body>
</html>
