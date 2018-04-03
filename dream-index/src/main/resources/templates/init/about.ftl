<#function about path>
    <#return request.requestURI==path ||
    request.requestURI?starts_with(path?ensure_ends_with("/")) ||
    (request.requestURI=='/aboutUs' && path='/aboutUs/intro')
    ||(request.requestURI=='/aboutUs/intro' && path='/aboutUs/intro')
    ||(request.requestURI=='/aboutUs/news' && path='/aboutUs/news')
    ||(request.requestURI=='/aboutUs/newsDetail' && path='/aboutUs/news')
    ||(request.requestURI=='/aboutUs/notices' && path='/aboutUs/notices')
    ||(request.requestURI=='/aboutUs/noticesDetail' && path='/aboutUs/notices')
    ||(request.requestURI=='/aboutUs/invite' && path='/aboutUs/invite')
    ||(request.requestURI=='/aboutUs/contact' && path='/aboutUs/contact')
    ||(request.requestURI=='/aboutUs/advice' && path='/aboutUs/advice')
    />
</#function>
<#function mAbout path>
    <#return about(path)?string("background:#337ab7;border:0px;color:white;", "")/>
</#function>
<#global about=about/>
<#global mAbout=mAbout/>
