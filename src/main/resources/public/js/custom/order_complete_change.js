    $(document).ready(function () {
           $('.isComplete:checkbox').each(function () {
               $(this).change(function(info){
                    var id = $(this).attr('id');
//                    $.ajax({
//                        type:"POST",
//                        data:{id, },
//                        url:"orders",
//                        success: function(data){
//                             console.log(data);
//                        }
//                    });
                     console.log(info);
               });

           });
        });