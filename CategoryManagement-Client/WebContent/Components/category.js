
$(document).on("click", "#btnSave", function(event)
{
	
	// Clear alerts------------------------------
	
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation----------------------------
	
	var status = validateCategoryForm();
	
	if(status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		
	}
	
	// If valid--------------------------------------
	
	var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "categoryAPI",
		type : type,
		data : $("#formCategory").serialize(),
		dataType : "text",
		complete : function(response,status)
		{
			onCategorySaveComplete(response.responseText,status);
				
	    }
	});

});


function validateCategoryForm(){
	
	//Code
	if ($("#categoryId").val().trim() == ""){
		
		return "Insert Category ID";		
	}
	
	//Name
	if ($("#categoryName").val().trim() == ""){
		
		return "Insert Category Name";
		
	}
	
	//Price
	if($("#categoryDesc").val().trim() == ""){
		
		return "Insert Category Description";
	
	}
	
	
	if($("#tagCode").val().trim() == ""){
		
		return "Insert Tag Code";
		
	}
	
	
	if($("#tagName").val().trim() == ""){
		
		return "Insert Tag Name";
		
	}
	
	return true;
	
}








function onCategorySaveComplete(response,status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("alertSuccess").show();
			
			$("#divCategoryGrid").html(resultSet.data);
			
			
			
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
			
		}	
		
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving."); 
		$("#alertError").show(); 
		
	} else
	{
		$("#alertError").text("Unknown Error while saving."); 
		$("#alertError").show(); 
		
	}	
	
	$("hidIDSave").val("");
	$("#formCategory")[0].reset();
	


}

$(document).on("click",".btnUpdate",function(event)
{
	$("#hidIDSave").val($(this).data("Id"));
	$("#categoryId").val($(this).closest("tr").find('td:eq(0)').text());
	$("#categoryName").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#categoryDesc").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#tagCode").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#tagName").val($(this).closest("tr").find('td:eq(4)').text());


});

$(document).on("click",".btnRemove",function(event)
{
	
	$.ajax(
	{
		url  : "categoryAPI",
		type : "DELETE",
		data : "ID=" + $(this).data("Id"),
		dataType : "text",
		complete : function(response,status)
		{
			
			onCategoryDeleteComplete(response.responseText,status);
		}
		
	});

});


function onCategoryDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divCategoryGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show();
		
		
 }


}
		






