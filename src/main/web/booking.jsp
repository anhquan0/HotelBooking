<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="model.entity.Service" %>

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
                  <form id="request" class="main_form" action="create-booking" method="POST">
                     <input type="hidden" name="roomID" value="<%= request.getAttribute("roomID") %>" >

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
                        <%
                           LocalDateTime checkInDate = (LocalDateTime) request.getAttribute("checkInDate");
                           LocalDateTime checkOutDate = (LocalDateTime) request.getAttribute("checkOutDate");
                        %>
                        <div class="col-md-12">
                             <span>Check-in Time</span>
                             <input class="contactus" type="datetime-local" name="check_in_time" value="<%= checkInDate %>">
                        </div>
                        <div class="col-md-12">
                             <span>Check-out Time</span>
                             <input class="contactus" type="datetime-local" name="check_out_time" value="<%= checkOutDate %>">
                        </div>
                        <div class="col-md-12">
                             <span>Service</span>
                             <select class="contactus" name="services" id="service" multiple>
                                 <% 
                                    List<Service> serviceList = (List<Service>) request.getAttribute("serviceList");
                                    for(int i = 0; i < serviceList.size(); i++) {
                                       Service service = serviceList.get(i);
                                 %>
                                    <option value="<%= service.getServiceId() %>"><%= service.getName() %> (<%= service.getPrice() %>$)</option>
                                 <%      
                                    }
                                 %>
                             </select>
                        </div>
                        <div class="col-md-12">
                           <span>Note</span>
                           <textarea name="note" class="contactus"></textarea>
                      </div>
                        <div class="col-md-12">
                             <span>Payment Method</span>
                             <div>
                                 <label class="payment_method">
                                    <input type="radio" name="payment" value="Cash"> <img src="images/icons8-cash-32.png"/>
                                 </label>
                                 <label class="payment_method">
                                    <input type="radio" name="payment" value="QR-Code"> <img width="30" height="30" src="https://img.icons8.com/ios-glyphs/60/qr-code--v1.png" alt="qr-code--v1"/>
                                 </label>
                             </div>
                        </div>
                        <div class="col-md-12">
                           <button class="send_btn">Submit</button>
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