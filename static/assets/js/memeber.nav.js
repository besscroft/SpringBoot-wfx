Vue.component("leftnav-mem",{
	template:`<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed">
						<a href="../bulletin.html" target="main_self_frame">系统首页</a>
					</li>
					<li class="layui-nav-item">
						<a href="product_list.html" target="main_self_frame">产品及文案</a>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">特色推广</a>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">订单与结算</a>
						<dl class="layui-nav-child">
							<dd><a href="popularize_order.html" target="main_self_frame">我推广的订单</a></dd>
							<dd><a href="record_order.html" target="main_self_frame">录单</a></dd>
							<dd><a href="javascript:;" target="main_self_frame">结算与返款</a></dd>
							<dd><a href="javascript:;" target="main_self_frame">订单结算流水</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">订单统计</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;" target="main_self_frame">商品推广统计</a></dd>
							<dd><a href="javascript:;" target="main_self_frame">渠道推广统计</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">资料设置</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;" target="main_self_frame">渠道设置</a></dd>
							<dd><a href="javascript:;" target="main_self_frame">基本信息</a></dd>
							<dd><a href="javascript:;" target="main_self_frame">修改密码</a></dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>`
});