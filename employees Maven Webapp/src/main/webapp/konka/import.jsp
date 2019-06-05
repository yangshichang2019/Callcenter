<%@ page language="java"  pageEncoding="UTF-8"%>

   <html>  
    <head>  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
    <title>读取Excel数据</title>  
    <style type="text/css">  
    div {  
        position: relative;  
        top: 120px;  
        text-align:center;  
    }  
    .submit {  
        clear: both;  
        position: relative;  
        border: 1px solid black;  
        background: blue;  
        color: white;  
        width: 70px;  
        height: 30px;  
        border-radius: 6px;  
        box-shadow: #000 3px 3px;  
        cursor: pointer;  
    }  
    .submit:hover{  
        background:#6D6DFF;  
    }  
    .submit:active{  
        top:3px;  
        left:3px;  
        box-shadow: #000 0px 0px;  
        background:#6D6DFF;  
    }  
    </style>  
    <script type="text/javascript">  
        function formCheck(form){  
            if(form.excelFile.value == ""){  
                alert("请选择上传的文件");  
                return false;  
            }else{  
                var str= form.excelFile.value;  
                var lc = str.toLowerCase();  
                var arr = lc.split(".");  
                var extname = arr[arr.length-1];  
                if(extname != "xls" && extname != "xlsx"){  
                     alert("请选择excel格式文件！");  
                     return false;  
                }  
            }  
        }  
    </script>  
    </head>  
    <body>  

    <form name="readExcel" action="/importExcel" method="post" enctype="multipart/form-data" onsubmit="return formCheck(this);">     
 
        Excel文件路径:&nbsp;<input type="file" name="excelFile"><br>  
    
        <input class="submit" type="submit" value="导入">  
      
    </form>  
   
    </body>  
    </html>  