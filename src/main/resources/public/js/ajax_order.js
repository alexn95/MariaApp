$(document).ready(function () {
           $('#make_order').click(function () {
               var surname = $('#recipient_surname').val();
               var name = $('#recipient_name').val();
               var phone = $('#recipient_phone').val();
               var note = $('#recipient_note').val();
               $.ajax({
                   type:"POST",
                   data:{surname, name, phone, note},
                   url:"test",
                   success: function(data){
                       console.log(data);
                   }
               });
           });
        });