    $(document).ready(function () {
           $('.isComplete:checkbox').each(function () {
               $(this).change(function(info){
                    var id = $(this).attr('id');
                    var isComplete = false;
                    if(this.checked) {
                        isComplete = true;
                    }

                    var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
                    var csrfToken = $("meta[name='_csrf']").attr("content");
                    var csrfHeader = $("meta[name='_csrf_header']").attr("content");  // THIS WAS ADDED
                    var data = {};
                    var headers = {};
                    data[csrfParameter] = csrfToken;
                    data["id"] = id;
                    data["isComplete"] = isComplete;
                    headers[csrfHeader] = csrfToken;

                    $.ajax({
                        type:"POST",

                        data: data,
                        url:"orders",
                        success: function(data){
                             console.log(data);
                        }
                    });
               });

           });
        });