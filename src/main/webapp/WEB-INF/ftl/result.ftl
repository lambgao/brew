<#include "macro-head.ftl">
<!DOCTYPE html>
<html>
<head>
<@head title="${appTitle}">
<meta name="keywords" content="${metaKeywords}"/>
<meta name="description" content=""/>
</@head>
</head>
<body>
	<#include "header.ftl">
	<div class="container ">
	  <div class="row-fluid">
	    <div class="span12">
	      	<h1>出错啦...</h1>
		<p>
		</p>
		<pre>
		    <code>
${result}
<#list result.allErrors as error >
	${error}
</#list>
		    </code>
		</pre>
	    </div>
	    <div class="span3">
	    </div>
	  </div>
	</div>
	<#include "footer.ftl">
</body>
</html>