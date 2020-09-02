Vue.component("leftnav-mem",{
	template:`<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed">
						<a class="" href="../bulletin.html">系统首页</a>
					</li>
					<li class="layui-nav-item">
						<a class="" href="javascript:;">产品及文案</a>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">特色推广</a>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">订单与结算</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;">我推广的订单</a></dd>
							<dd><a href="javascript:;">结算与返款</a></dd>
							<dd><a href="javascript:;">订单结算流水</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">订单统计</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;">商品推广统计</a></dd>
							<dd><a href="javascript:;">渠道推广统计</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">资料设置</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;">渠道设置</a></dd>
							<dd><a href="javascript:;">基本信息</a></dd>
							<dd><a href="javascript:;">修改密码</a></dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>`
});