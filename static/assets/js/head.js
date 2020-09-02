Vue.component("myhead",{
	template:`<div class="layui-header">
			<div class="layui-logo">{{title}}</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">管理系统</a></li>
				<li class="layui-nav-item"><a href="">商户系统</a></li>
				<li class="layui-nav-item"><a href="">自媒体系统</a></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
					<a href="javascript:;">
						<img src="https://www.52bess.com/uploads/avatar.png" class="layui-nav-img">
						{{username}}
					</a>
					<dl class="layui-nav-child">
						<dd><a href="">基本资料</a></dd>
						<dd><a href="">安全设置</a></dd>
					</dl>
				</li>
				<li class="layui-nav-item"><a href="javascript:;">退出</a></li>
			</ul>
		</div>`,
	props:["title","username"]
});