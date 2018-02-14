    $(document).ready(function () {
           $('.isComplete:checkbox').each(function () {
               $(this).change(function(){
                    var id = $(this).attr('id');
                    console.log(id);
               });
//               $.ajax({
//                   type:"POST",
//                   data:{surname, name, phone, note},
//                   url:"",
//                   success: function(data){
//                       console.log(data);
//                       $('#orderModal').modal("hide");
//                       $('#resultOrderModal').modal("show");
//                   }
//               });

           });
        });