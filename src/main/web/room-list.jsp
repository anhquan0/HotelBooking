<%@ page import="model.entity.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
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
                     <h2>Our Room</h2>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- our_room -->
      <div  class="our_room">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <div class="titlepage">
                     <h2>Filter Rooms</h2>
                     <div class="filter-group">
                        <select id="priceFilter" class="form-control filter-select" onchange="filterRooms()">
                           <option value="all">All Prices</option>
                           <option value="low">Low to High</option>
                           <option value="high">High to Low</option>
                        </select>
                        <select id="statusFilter" class="form-control filter-select" onchange="filterRooms()">
                           <option value="all">All Status</option>
                           <option value="available">Available</option>
                           <option value="unavailable">Unavailable</option>
                        </select>
                        <select id="typeFilter" class="form-control filter-select" onchange="filterRooms()">
                           <option value="all">All Types</option>
                           <option value="Standard">Standard</option>
                           <option value="Normal">Normal</option>
                           <option value="Superior">Superior</option>
                           <option value="Vip">Vip</option>
                        </select>
                        <button id="filterBtn" class="btn btn-primary" onclick="resetFilter()">Reset Filter</button>
                     </div>
                  </div>
               </div>
            </div>
            <div class="row">
               <%
                  LocalDate checkInDate = (LocalDate) request.getAttribute("checkInDate");
                  LocalDate checkOutDate = (LocalDate) request.getAttribute("checkOutDate");
               %>
               <div class="col-md-6 col-md-12">
                  <label for="check_in_date">Check In Date: </label>
                  <input type="date" id="check_in_date" value="<%= checkInDate != null ? checkInDate : "" %>">
               </div>
               <div class="col-md-6 col-md-12">
                  <label for="check_out_date">Check Out Date: </label>
                  <input type="date" id="check_out_date" value="<%= checkOutDate != null ? checkOutDate : "" %>">
               </div>
            </div>
            <div class="row" id="roomList">
               <%
                  List<Room> roomList = (List<Room>) request.getAttribute("roomList");
                  int pageSize = 6;
                  for (int i = 0; i < roomList.size(); i++) {
                     Room room = roomList.get(i);
               %>
               <div class="col-md-4 col-sm-6 room-item" style="display: <%=i < pageSize ? "block" : "none"%>">
                  <div id="serv_hover"  class="room <%=room.getStatus().equals("Available") ? "" : "unavailable"%>">
                     <div class="room_img">
                        <figure><img src="<%=room.getIMG()%>" alt="#"/></figure>
                     </div>
                     <div class="room_info">
                        <h3><%=room.getRoomType()%></h3>
                        <p class="room_price">$<%=room.getPrice()%>/day</p>
                        <p class="bed_room"><%=room.getDescription()%></p>
                        <% if(room.getStatus().equals("Available")) { %>
                        <a href="booking.jsp?roomID=<%=room.getRoomId()%>" class="book_btn">Book now</a>
                        <% } %>
                     </div>
                  </div>
               </div>
               <%
                  }
               %>
            </div>
            <button id="loadMoreBtn" class="btn btn-primary">Load More</button>
            <script>
               var roomItems = document.querySelectorAll('.room-item');
               var loadMoreBtn = document.getElementById('loadMoreBtn');
               var pageSize = <%=pageSize%>;
               var currentPage = 1;

               loadMoreBtn.addEventListener('click', function() {
                  currentPage++;
                  for (var i = 0; i < roomItems.length; i++) {
                     if (i < currentPage * pageSize) {
                        roomItems[i].style.display = 'block';
                     }
                  }
                  if (currentPage * pageSize >= roomItems.length) {
                     loadMoreBtn.style.display = 'none';
                  }
               });
            </script>
         </div>
      </div>
      <!-- end our_room -->
     
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

<style>
   .unavailable {
      opacity: 0.5;
      pointer-events: none;
   }
   .bed_room p {
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      width: 100%;
   }
   .room_info {
      background: ghostwhite;
      padding: 10px;
   }

   .room_price {
      font-weight: bold;
      color: red;
   }
   .titlepage {
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-bottom: 20px;
   }

   .filter-group {
      display: flex;
      justify-content: space-between;
      width: 100%;
      max-width: 600px;
      margin-top: 20px;
   }

   .filter-select {
      flex: 1;
      margin-right: 10px;
   }

   #filterBtn {
      width: fit-content;
      background-color: red;
      border: none;
      color: whitesmoke;
      padding: 10px 24px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
      margin: 0px 2px;
      transition-duration: 0.4s;
      cursor: pointer;
      height: fit-content;
   }

   #filterBtn:hover {
      background-color: #ffffff;
      color: red;
   }

   #loadMoreBtn {
      display: block;
      width: 200px;
      height: 45px;
      margin: 20px auto;
      background-color: #ffffff;
      color: #000000;
      border: 2px solid red;
      border-radius: 5px;
      font-size: 18px;
      transition: background-color 0.3s ease;
   }

   #loadMoreBtn:hover {
      background-color: red;
   }
</style>

   <script>
      function filterRooms() {
         // Get selected values
         var priceFilter = document.getElementById('priceFilter').value;
         var statusFilter = document.getElementById('statusFilter').value;
         var typeFilter = document.getElementById('typeFilter').value;

         // Build the URL with the selected values
         var url = 'room-list?priceFilter=' + priceFilter + '&statusFilter=' + statusFilter + '&typeFilter=' + typeFilter;

         // Redirect to the new URL
         window.location.href = url;
      }

      function resetFilter() {
         // Redirect to the original page without any filters
         window.location.href = 'room-list';
      }

      // Set the selected option of the dropdowns based on the URL parameters
      window.onload = function() {
         var urlParams = new URLSearchParams(window.location.search);
         document.getElementById('priceFilter').value = urlParams.get('priceFilter') || 'all';
         document.getElementById('statusFilter').value = urlParams.get('statusFilter') || 'all';
         document.getElementById('typeFilter').value = urlParams.get('typeFilter') || 'all';
      }
   </script>

</html>