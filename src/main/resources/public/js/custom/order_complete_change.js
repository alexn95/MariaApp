    $(document).ready(function () {
           $('.isComplete:checkbox').each(function () {
               $(this).change(function(info){
                   var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
                   var csrfToken = $("meta[name='_csrf']").attr("content");
                   var csrfHeader = $("meta[name='_csrf_header']").attr("content");
                   var data = {}
                   data[csrfParameter] = csrfToken;
                   data[id] = id;
                   data[isComplete] = isComplete;
                   headers[csrfHeader] = csrfToken;
                    var id = $(this).attr('id');
                    var isComplete = false;
                    if(this.checked) {
                        isComplete = true;
                    }
                    $.ajax({
                        type:"POST",
                        data: data,
                        url:"orders",
                        headers: headers,
                        success: function(data){
                             console.log(data);
                        }
                    });
               });

           });
        });