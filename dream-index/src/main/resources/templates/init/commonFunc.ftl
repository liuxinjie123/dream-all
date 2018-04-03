
<#-- Add by Eno -->

<#-- Return "selected" for selected path.
    (path2 & path3 are not necessary)
 -->
<#function selected path path2 path3>
    <#if (request.requestURI == path) || (request.requestURI == path2) || (request.requestURI == path3)>
    <#--<#if (request.requestURI?index_of(path) !=-1 )>-->
        <#return "selected"/>
    </#if>
</#function>

<#-- Return returnVal if param==param2.
param: Obj, param2: Str
 -->
<#function selected2 param param2 returnVal>
    <#if param??>
        <#if param == param2>
            <#return returnVal/>
        </#if>
    <#else>
    </#if>
</#function>

<#function handleBlank val unit="">
    <#if val?length == 0>
        <#return '--'>
    <#else>
        <#return val+unit>
    </#if>
</#function>

<#--表格按列排序图标class sort
sortType：后台排序名 String
sortOrder：后台排序方式，int 0升序, 1降序
currentLine：当前所在列名

sortType!=currentLine  默认返回sort
sortType==currentLine && sortOrder=1  返回 sortDown
sortType==currentLine && sortOrder=2  返回 sortUp
参考例子：/kitt/site/src/main/resources/templates/person/mySupply.ftl
-->
<#function sort sortType sortOrder currentLine>
    <#if !sortType?? || !sortOrder?? || !currentLine??>
        <#return "sort"/>
    <#elseif sortType == currentLine>
        <#if sortOrder == "desc">
            <#return "sortDown"/>
        <#elseif sortOrder == "asc">
            <#return "sortUp"/>
        </#if>
    <#else>
        <#return "sort"/>
    </#if>
</#function>

<#-- show range

用法：
1. 引入方法：在页面<@block name="body">里引入<#include "/init/commonFunc.ftl" >

2. 调用：把两个区间值传进showRange方法，默认自动补上单位%
例如： ${showRange(demand.ADV, demand.ADV02)}

3. 如果有特殊单位的，把单位传进去，默认为"%"
例如：
${showRange(demand.GV, demand.GV02, "")}
${showRange(demand.YV, demand.YV02, "mm")}

4. 需要格式化成两位小数的调用 showRange4fmt方法
-->
<#function showRange val1 val2 unit = "%">
    <#local result="" val1=val1!0 val2=val2!0/>
    <#local val1=val1?c val2=val2?c/>


    <#if val1 != 0 || val2 != 0>
        <#if val1 == val2>
            <#local result = val1 />
        <#else>
            <#local result = val1 />
            <#if val2 != 0>
                <#local result = result + " - " + val2 />
            </#if>
        </#if>
        <#local result = result + unit/>
    <#else>
        <#local result="-" />
    </#if>
    <#return result />
</#function>

<#function showSingle val1  unit = "%">
    <#local result="" val1=val1!0 />
    <#local val1=val1?c />

    <#if val1 !=0 >
        <#local result = val1 + unit/>
    <#else >
        <#local result="-" />
    </#if>
    <#return result />
</#function>


<#-- show range (format: 0.00) -->
    <#function showRange4fmt val1 val2  unit fmtType = 0 >
    <#local result="" val1=val1!0 val2=val2!0 separator = " - "  val2renderer = ""  />

    <#if val1 != 0 || val2 != 0>
        <#if val1 != val2 && val2!=0>
            <#if fmtType = 1>
                <#local val2renderer = separator + val2?string("0.00") />
            <#elseif fmtType == 2>
                <#local val2renderer = separator + val2?c />
            <#else>
                <#local val2renderer = separator + val2 />
            </#if>
        </#if>
        <#if fmtType = 1>
            <#local result = val1?string("0.00") + val2renderer + unit />
        <#elseif fmtType == 2>
            <#local result = val1?c + val2renderer + unit />
        <#else>
            <#local result = val1 + val2renderer + unit />
        </#if>
    <#else>
        <#local result="-" />
    </#if>
    <#return result />
</#function>

<#--给标题添加组合信息的title-->
<#function assemblyInfoForTitle deliveryplace deliveryprovince  otherplace coaltype  NCV NCV02>
    <#local result="" deliveryplace=deliveryplace deliveryprovince=deliveryprovince otherplace=otherplace coaltype=coaltype NCV=NCV NCV02=NCV02 />
    <#if deliveryplace == "其它">
        <#local result = deliveryprovince + otherplace/>
    <#else>
        <#local result = deliveryprovince + deliveryplace/>
    </#if>
    <#local result = result + "采购"/>

    <#if coaltype!="焦煤">
        <#if NCV02?? && NCV02 !=0 >
            <#local result = result +NCV02?c+"大卡"/>
        <#elseif NCV?? && NCV != 0>
            <#local result = result +NCV?c+"大卡"/>
        </#if>
    </#if>
    <#local result = result + coaltype/>
    <#return result />
</#function>

<#--给标题添加组合信息的title-->
<#function infoTitleAppend deliveryprovince deliveryplace otherplace originplace coaltype NCV02>
    <#local result="" deliveryprovince=deliveryprovince deliveryplace=deliveryplace otherplace=otherplace originplace=originplace coaltype=coaltype NCV02=NCV02 />
    <#if deliveryplace == "其它">
        <#local result = deliveryprovince + otherplace/>
    <#else>
        <#local result = deliveryprovince + deliveryplace/>
    </#if>
    <#local result = result + originplace/>
    <#if coaltype!="焦煤" && NCV02 !=0>
        <#local result = result +NCV02?c+"大卡"/>
    </#if>
    <#local result = result + coaltype/>
    <#return result />
</#function>

<#function setTextOverflow text length>
    <#if text?length lte length>
        <#return text>
    <#else>
        <#return text?substring(0,length) + '...'>
    </#if>
</#function>