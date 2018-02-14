$(document).ready(function () {
           $('#make_order').click(function () {
               var surname = $('#recipient_surname').val();
               var name = $('#recipient_name').val();
               var phone = $('#recipient_phone').val();
               var note = $('#recipient_note').val();

               var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
               var csrfToken = $("meta[name='_csrf']").attr("content");
               var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
               var data = {surname, name, phone, note};
               var headers = {};
               data[csrfParameter] = csrfToken;
               headers[csrfHeader] = csrfToken;

               $.ajax({
                   type:"POST",
                   data: data,
                   url:"",
                   success: function(data){
                       console.log(data);
                       $('#orderModal').modal("hide");
                       $('#resultOrderModal').modal("show");
                   }
               });
           });
        });