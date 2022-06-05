$(document).ready(function (){
    $("form#ajax").on("submit", function (){
        console.log("Function started");
        var formSettings = $(this),
            url = formSettings.attr("action"),
            method = formSettings.attr("method"),
            data = {};
        formSettings.find("[name]").each(function (index, value){
            console.log("Form submission Detected");
            var formAttributes = $(this),
                name = formAttributes.attr("name"),
                value = formAttributes.val();
            data[name] = value;
        })

        $.ajax({
            url: url,
            type: method,
            data: data,
            success: function (response){
                //console.log(response);
                let startTag = "<!-- top Search -->";
                let endTag = "<!-- bottom Search -->";
                let start = response.lastIndexOf(startTag);
                let end = response.lastIndexOf(endTag) + endTag.length;
                //console.log(start);
                //console.log(end)
                //console.log(endTag.length)
                let updated = response.substring(start, end);
                console.log(updated)
                //document.getElementById("searchResult").outerHTML = updated;
                document.getElementById("searchResult").innerHTML= updated;
            }
        });
        return false;
    })
});
//
// $(document).ready(function (){
//     $("form#ajax23").on("submit", function (){
//         console.log("Function started");
//         var formSettings = $(this),
//             url = formSettings.attr("action"),
//             method = formSettings.attr("method"),
//             data = {};
//         formSettings.find("[name]").each(function (index, value){
//             console.log("Form submission Detected");
//             var formAttributes = $(this),
//                 name = formAttributes.attr("name"),
//                 value = formAttributes.val();
//             data[name] = value;
//         })
//
//         $.ajax({
//             url: url,
//             type: method,
//             data: data,
//             success: function (response){
//                 console.log(response);
//                 let startTag = "<!--Top of ManagerForm--->";
//                 let endTag = "<!--Bottom of ManagerForm--->";
//                 let start = response.lastIndexOf(startTag);
//                 //let end = response.lastIndexOf(endTag) + endTag.length;
//                 //console.log(start);
//                 //console.log(end)
//                 //console.log(endTag.length)
//                 //let updated = response.substring(start, end);
//                 //document.getElementById("ManagerFormContent").innerHTML = updated;
//             }
//         });
//         return false;
//     })
// })
// $(document).ready(function (){
//     $("form#ajax2").on("submit", function (){
//         alert("Java Recongnizes this form")
//     })
// })
$(document).ready(function (){
    setInterval(function (){
        let errors = document.getElementsByClassName("errors");
      console.log(document.getElementsByClassName("errors"));
        for (let i = 0; i < errors.length; i++) {
            errors[i].hidden = true;
        }
    },5000)
});