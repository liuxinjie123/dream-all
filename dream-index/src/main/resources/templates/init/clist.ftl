<#macro cselect list value="" name="" class="" id="" style="">
    <#local class="cselect "+class>
    <#if !list?is_hash>
        <#local list1={}>
        <#list list as val>
            <#local list1=list1+{val:val}>
        </#list>
        <#local list=list1>
    </#if>
    <#if !list[value]??>
        <#local value=list?keys?first>
    </#if>

    <div <#if id!=''>id="${id}"</#if> class="${class}" <#if style!=''>style="${style}"</#if> >
        <#if name!=''>
            <input id="${name!}" name="${name!}" type="hidden" value="${value!}"/>
        </#if>
        <label>${list[value]}</label>
        <ul>
            <#list list?keys as key>
                <li key="${key!}" <#if key==value>selected="true"</#if>>${list[key]}</li>
            </#list>
        </ul>
        <span class="trigger"></span>
    </div>
</#macro>
<#--<#global cselect=cselect/>-->