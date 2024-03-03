<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
   <head>
      <!-- basic -->
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <!-- mobile metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="viewport" content="initial-scale=1, maximum-scale=1">
      <!-- site metas -->
      <title>keto</title>
      <meta name="keywords" content="">
      <meta name="description" content="">
      <meta name="author" content="">
      <!-- bootstrap css -->
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <!-- style css -->
      <link rel="stylesheet" href="css/style.css">
      <!-- Responsive-->
      <link rel="stylesheet" href="css/responsive.css">
      <!-- fevicon -->
      <link rel="icon" href="images/fevicon.png" type="image/gif" />
      <!-- Scrollbar Custom CSS -->
      <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
      <!-- Tweaks for older IEs-->
      <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
   </head>
   <!-- body -->
   <body class="main-layout">
      <!-- loader  -->
      <div class="loader_bg">
         <div class="loader"><img src="images/loading.gif" alt="#"/></div>
      </div>
      <!-- end loader -->
      <!-- header -->
      <%@include file="header.jsp" %>
      <!-- end header inner -->
      <!-- end header -->
     <div class="back_re">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <div class="title">
                      <h2>Booking Room</h2>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!--  contact -->
      <div class="contact">
         <div class="container">
            <div class="row">
               <div class="col-md-6">
                  <form id="request" class="main_form">
                     <div class="row">
                        <div class="col-md-12 ">
                           <span>Name</span>
                           <input class="contactus" placeholder="Name" type="type" name="Name">
                        </div>
                        <div class="col-md-12">
                           <span>Email</span>
                           <input class="contactus" placeholder="Email" type="type" name="Email">
                        </div>
                        <div class="col-md-12">
                           <span>Phone Number</span>
                           <input class="contactus" placeholder="Phone Number" type="type" name="PhoneNumber">
                        </div>
                        <div class="col-md-12">
                             <span>Check-in Time</span>
                             <input class="contactus" placeholder="dd/mm/yyyy" type="date" name="dd/mm/yyyy">
                        </div>
                        <div class="col-md-12">
                             <span>Check-out Plan Time</span>
                             <input class="contactus" placeholder="dd/mm/yyyy" type="date" name="dd/mm/yyyy">
                        </div>
                        <div class="col-md-12">
                             <span>Service</span>
                        </div>
                        <div class="col-md-12">
                             <span>Payment Method</span>
                             <div>
                                 <label class="payment_method">
                                    <input type="radio" name="payment" value="cash"> <img src="images/icons8-cash-32.png"/>
                                 </label>
                                 <label class="payment_method">
                                    <input type="radio" name="payment" value="paypal"> <img src="images/icons8-paypal-32.png"/>
                                 </label>
                             </div>
                        </div>
                        <div class="col-md-12">
                           <button class="send_btn">Send</button>
                        </div>

                     </div>
                  </form>
               </div>
            </div>
         </div>
      </div>
      <!-- end contact -->
      <!--  footer -->
      <%@include file="footer.jsp" %>
      <!-- end footer -->
      <!-- Javascript files-->
      <script src="js/jquery.min.js"></script>
      <script src="js/bootstrap.bundle.min.js"></script>
      <script src="js/jquery-3.0.0.min.js"></script>
      <!-- sidebar -->
      <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
      <script src="js/custom.js"></script>
   </body>
</html>