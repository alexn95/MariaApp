    $(document).ready(function () {
           $('.isComplete:checkbox').each(function () {
               $(this).change(function(info){
                    var id = $(this).attr('id');
                    var isComplete = false;
                    if(this.checked) {
                        isComplete = true;
                    }
                    $.ajax({
                        type:"POST",
                        data:{id, isComplete},
                        url:"orders",
                        success: function(data){
                             console.log(data);
                        }
                    });
               });

           });
        });