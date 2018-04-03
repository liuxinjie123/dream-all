<#macro noParamPager path page pagesize count class="" id="">
    <#local page=page/>
    <#local pagesize=pagesize/>
    <#local pageNumber=pagesize />
    <#local maxpage=(count/pagesize)?ceiling/>
    <#if maxpage lt 1>
        <#local maxpage=1/>
    </#if>
    <#if page lt 1>
        <#local page=1/>
    </#if>
    <#if page gt maxpage>
        <#local maxpage=page/>
    </#if>
    <#local begin=page-2/>
    <#if begin lt 1>
        <#local begin=1/>
    </#if>
    <#local end=begin+4/>
    <#if end gt maxpage>
        <#local end=maxpage/>
        <#local begin=end-5/>
        <#if begin lt 1>
            <#local begin=1/>
        </#if>
    </#if>
<input type="hidden" id="count" value="${count!'0'}">
<input type="hidden" id="currentPage" value="${page!'0'}">
    <#if count gt 0 && maxpage gt 1>
    <div class="pagination-wrap">
        <div<#if id!=''> id="${id}"</#if> class="ym-pagination">
            <ul>
                <li class="prev <#if page == 1>disabled</#if>">
                    <a href="javascript:;"><i class="ico ico-page-prev"></i>上一页</a>
                </li>
            <#-- maxpage <= 5 -->
                <#if maxpage lt 6>
                <#-- page=1 -->
                    <#if page lt 2>
                        <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                        <#if maxpage gt 1>
                            <#list 2 .. maxpage as index>
                                <li>
                                    <a href="javascript:void(0);">${index}</a>
                                </li>
                            </#list>
                        </#if>
                    <#else>
                    <#-- 2<= page <=5 -->
                        <#list 1 .. page-1 as index>
                            <li>
                                <a href="javascript:void(0);">${index}</a>
                            </li>
                        </#list>
                        <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                        <#if page < maxpage>
                            <#list page+1 .. maxpage as index>
                                <li>
                                    <a href="javascript:void(0);">${index}</a>
                                </li>
                            </#list>
                        </#if>
                    </#if>
                <#else>
                <#-- maxpage >= 6  -->
                    <#if page lt 5>
                    <#-- page = 1 -->
                        <#if page lt 2>
                            <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                            <#list 2 .. end-1 as index>
                                <li>
                                    <a href="javascript:void(0);">${index}</a>
                                </li>
                            </#list>
                            <li>
                                <a href="javascript:void(0);">${end}</a>
                            </li>
                            <li class="disabled ellipse"><span>…</span></li>
                        <#-- page =4 -->
                        <#elseif page gt 3>
                            <#list 1 .. page-1 as index>
                                <li>
                                    <a href="javascript:void(0);">${index}</a>
                                </li>
                            </#list>
                            <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                            <li>
                                <a href="javascript:void(0);">5</a>
                            </li>
                            <li class="disabled ellipse"><span>…</span></li>
                        <#-- 2<= page <=3 -->
                        <#else>
                            <#list 1 .. page-1 as index>
                                <li>
                                    <a href="javascript:void(0);">${index}</a>
                                </li>
                            </#list>
                            <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                            <#list page+1 .. 4 as index>
                                <li>
                                    <a href="javascript:void(0);">${index}</a>
                                </li>
                            </#list>
                            <li>
                                <a href="javascript:void(0);">5</a>
                            </li>
                            <li class="disabled ellipse"><span>…</span></li>
                        </#if>
                    <#-- page >= 5 -->
                    <#else>
                        <li>
                            <a href="javascript:void(0);">1</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);">2</a>
                        </li>
                        <li class="disabled ellipse"><span>…</span></li>
                        <#if page+1 gt maxpage>
                            <#list page-2 .. page-1 as index>
                                <li>
                                    <a href="javascript:void(0);">${index?c}</a>
                                </li>
                            </#list>
                            <li class="active"><a href="javascript:;" class="current">${page?c}</a></li>
                        <#elseif page+2 gt maxpage>
                            <#list page-1 .. page-1 as index>
                                <li>
                                    <a href="javascript:void(0);">${index?c}</a>
                                </li>
                            </#list>
                            <li class="active"><a href="javascript:;" class="current">${page?c}</a></li>
                            <#list page+1 .. page+1 as index>
                                <li>
                                    <a href="javascript:void(0);">${index?c}</a>
                                </li>
                            </#list>
                        <#elseif page+3 gt maxpage>
                            <#list page-1 .. page-1 as index>
                                <li>
                                    <a href="javascript:void(0);">${index?c}</a>
                                </li>
                            </#list>
                            <li class="active"><a href="javascript:;" class="current">${page?c}</a></li>
                            <#list page+1 .. page+1 as index>
                                <li>
                                    <a href="javascript:void(0);">${index?c}</a>
                                </li>
                            </#list>
                            <li class="disabled ellipse"><span>…</span></li>
                        <#else>
                            <li>
                                <a href="javascript:void(0);">${(page-1)?c}</a>
                            </li>
                            <li class="active"><a href="javascript:;" class="current">${page?c}</a></li>
                            <li>
                                <a href="javascript:void(0);">${(page+1)?c}</a>
                            </li>
                            <li class="disabled ellipse"><span>…</span></li>
                        </#if>
                    </#if>
                </#if>
                <#if maxpage gt 1>
                    <li class="next <#if page == maxpage>disabled</#if>">
                        <a href="javascript:void(0);">下一页<i class="ico ico-page-next"></i></a>
                    </li>
                </#if>
            </ul>
            <div class="total">共 ${maxpage?c}  页，</div> <div class="form"><span class="text">到第 </span> <input class="ym-pagination-input" id="J_ym-pagination-input" type="text" value="${page?c}" maxlength="4"><span class="text">页</span> <span class="ym-pagination-sbm-btn" id="J_ym-pagination-sbm-btn">确定</span></div>
        </div>
    </div>
    <div class="clearfix"></div>

    </#if>
</#macro>
<#global noParamPager=noParamPager/>