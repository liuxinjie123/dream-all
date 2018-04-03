<#macro pager2 path params page pagesize count   class="" id="" isajax=false >
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
                    <span href="<#if page == 1>javascript:;<#else>${url(path, params+{"page":page-1, "pagesize": pagesize})}</#if>"><i class="ico ico-page-prev"></i>上一页</span>
                </li>
            <#-- maxpage <= 5 -->
                <#if maxpage lt 6>
                <#-- page=1 -->
                    <#if page lt 2>
                        <li class="active"><span href="javascript:;" class="current">${page}</span></li>
                        <#if maxpage gt 1>
                            <#list 2 .. maxpage as index>
                                <li>
                                    <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</span>
                                </li>
                            </#list>
                        </#if>
                    <#else>
                    <#-- 2<= page <=5 -->
                        <#list 1 .. page-1 as index>
                            <li>
                                <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</span>
                            </li>
                        </#list>
                        <li class="active"><span href="javascript:;" class="current">${page}</span></li>
                        <#if page < maxpage>
                            <#list page+1 .. maxpage as index>
                                <li>
                                    <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</span>
                                </li>
                            </#list>
                        </#if>
                    </#if>
                <#else>
                <#-- maxpage >= 6  -->
                    <#if page lt 5>
                    <#-- page = 1 -->
                        <#if page lt 2>
                            <li class="active"><span href="javascript:;" class="current">${page}</span></li>
                            <#list 2 .. end-1 as index>
                                <li>
                                    <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</span>
                                </li>
                            </#list>
                            <li>
                                <span href="${url(path, params+{"page":end, "pagesize": pagesize})}">${end}</span>
                            </li>
                            <li class="disabled ellipse"><span>…</span></li>
                        <#-- page =4 -->
                        <#elseif page gt 3>
                            <#list 1 .. page-1 as index>
                                <li>
                                    <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</span>
                                </li>
                            </#list>
                            <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                            <li>
                                <span href="${url(path, params+{"page":5, "pagesize": pagesize})}">5</span>
                            </li>
                            <li class="disabled ellipse"><span>…</span></li>
                        <#-- 2<= page <=3 -->
                        <#else>
                            <#list 1 .. page-1 as index>
                                <li>
                                    <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</span>
                                </li>
                            </#list>
                            <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                            <#list page+1 .. 4 as index>
                                <li>
                                    <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</span>
                                </li>
                            </#list>
                            <li>
                                <span href="${url(path, params+{"page":5, "pagesize": pagesize})}">5</span>
                            </li>
                            <li class="disabled ellipse"><span>…</span></li>
                        </#if>
                    <#-- page >= 5 -->
                    <#else>
                        <li>
                            <span href="${url(path, params+{"page":1, "pagesize": pagesize})}">1</span>
                        </li>
                        <li>
                            <span href="${url(path, params+{"page":2, "pagesize": pagesize})}">2</span>
                        </li>
                        <li class="disabled ellipse"><span>…</span></li>
                        <#if page+1 gt maxpage>
                            <#list page-2 .. page-1 as index>
                                <li>
                                    <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index?c}</span>
                                </li>
                            </#list>
                            <li class="active"><span href="javascript:;" class="current">${page?c}</span></li>
                        <#elseif page+2 gt maxpage>
                            <#list page-1 .. page-1 as index>
                                <li>
                                    <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index?c}</span>
                                </li>
                            </#list>
                            <li class="active"><span href="javascript:;" class="current">${page?c}</span></li>
                            <#list page+1 .. page+1 as index>
                                <li>
                                    <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index?c}</span>
                                </li>
                            </#list>
                        <#elseif page+3 gt maxpage>
                            <#list page-1 .. page-1 as index>
                                <li>
                                    <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index?c}</span>
                                </li>
                            </#list>
                            <li class="active"><span href="javascript:;" class="current">${page?c}</span></li>
                            <#list page+1 .. page+1 as index>
                                <li>
                                    <span href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index?c}</span>
                                </li>
                            </#list>
                            <li class="disabled ellipse"><span>…</span></li>
                        <#else>
                            <li>
                                <span href="${url(path, params+{"page":page-1, "pagesize": pagesize})}">${(page-1)?c}</span>
                            </li>
                            <li class="active"><span href="javascript:;" class="current">${page?c}</span></li>
                            <li>
                                <span href="${url(path, params+{"page":page+1, "pagesize": pagesize})}">${(page+1)?c}</span>
                            </li>
                            <li class="disabled ellipse"><span>…</span></li>
                        </#if>
                    </#if>
                </#if>
                <#if maxpage gt 1>
                    <li class="next <#if page == maxpage>disabled</#if>">
                        <span href="<#if page == maxpage>javascript:;><#else>${url(path, params+{"page":page+1, "pagesize": pagesize})}</#if>">下一页<i
                                class="ico ico-page-next"></i></span>
                    </li>
                </#if>
            </ul>
            <div class="total">共 ${maxpage?c} 页</div>
            <#--<div class="form"><span class="text">到第 </span> <input class="ym-pagination-input"-->
                                                                   <#--id="J_ym-pagination-input" type="text"-->
                                                                   <#--value="${page?c}" maxlength="4"><span-->
                    <#--class="text">页</span> <span class="ym-pagination-sbm-btn" id="J_ym-pagination-sbm-btn">确定</span>-->
            <#--</div>-->
        </div>
    </div>
    <div class="clearfix"></div>
    <script>
        (function () {
            var input = document.getElementById("J_ym-pagination-input");
            var updateURLParameter = function (url, param, paramVal) {
                var newAdditionalURL = "";
                var tempArray = url.split("?");
                var baseURL = tempArray[0];
                var additionalURL = tempArray[1];
                var temp = "";
                if (additionalURL) {
                    tempArray = additionalURL.split("&");
                    for (i = 0; i < tempArray.length; i++) {
                        if (tempArray[i].split('=')[0] != param) {
                            newAdditionalURL += temp + decodeURIComponent(tempArray[i]);
                            temp = "&";
                        }
                    }
                }
                var rows_txt = temp + "" + param + "=" + paramVal;
                return baseURL + "?" + newAdditionalURL + rows_txt;
            }
            <#--input.onkeyup = function () {-->
                <#--this.value = this.value.replace(/\D|\s/g, "");-->
            <#--};-->
            <#--document.getElementById("J_ym-pagination-sbm-btn").onclick = function () {-->
                <#--var val = parseInt(input.value);-->
                <#--if (isNaN(val) || val === 0 || val > ${maxpage?c}) {-->
                    <#--input.select();-->
                    <#--return;-->
                <#--}-->
                <#--location.href = updateURLParameter(location.href, "page", val);-->
            <#--};-->
            //分页标签
            $(".ym-pagination span:not(.ym-pagination-sbm-btn)").click(function(){
                if(${isajax}){
                    $.ajax({
                        url:$(this).attr("href"),
                        type:'GET',
                        dataType:'html',
                        success:function(data){
                            $("#tenderInventBlock").modal('show');
                            $("#mySupplyerlistWrap").html(data);


                            //操作文字修改
                            var haveLi=$(".haveinventLists li").length;
                            if(haveLi>0){

                                $(".mySupplyerlist").each(function(){
                                    var thisUserId=$(this).children(".control").children("a").data("userid");
                                    var thisIndexNow=$(this).index();
                                    $(".haveinventLists li").each(function(){
                                        var innerUserId=$(this).children("span").data("userid");
                                        if(innerUserId==thisUserId){
                                            $(".control a").eq(thisIndexNow-1).text("已邀请").attr("class","haveInvent");
                                        }

                                    })
                                });


                            }
                            //投标邀请已邀请文字变化
//                            $(".control").children(".noInvent").each(function(){
//                                var userId=$(this).data("userid");
//                                for(var i=0;i<tenderinviteids.length;i++){
//                                    if(tenderinviteids[i]==userId){
//                                        $(this).text("已邀请").attr("class","haveInvent");
//                                    }else{
//                                        $(this).text("邀请").attr("class","noInvent");
//                                    }
//                                }
//                            });



                        }
                    });
                }else{
                    location.href = $(this).attr("href");
                }
            });

        }());
    </script>
    </#if>
</#macro>
<#global pager2=pager2/>