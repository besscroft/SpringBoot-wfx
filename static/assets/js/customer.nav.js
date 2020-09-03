Vue.component("leftnav-cus",{
	template:`<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed">
						<a class="" href="../bulletin.html" target="main_self_frame">系统首页</a>
					</li>
					<li class="layui-nav-item">
						<a class="" href="javascript:;">商品管理</a>
						<dl class="layui-nav-child">
							<dd><a href="product_list.html" target="main_self_frame">商品信息</a></dd>
							<dd><a href="product_add.html" target="main_self_frame">增加商品</a></dd>
							<dd><a href="copy_manager.html" target="main_self_frame">文案管理</a></dd>
							<dd><a href="copy_add.html" target="main_self_frame">增加文案</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">订单管理</a>
						<dl class="layui-nav-child">
							<dd><a href="order_list.html" target="main_self_frame">全部订单</a></dd>
							<dd><a href="order_state0_list.html" target="main_self_frame">已下单订单</a></dd>
							<dd><a href="order_state1_list.html" target="main_self_frame">待跟进订单</a></dd>
							<dd><a href="order_state2_list.html" target="main_self_frame">待发货订单</a></dd>
							<dd><a href="order_state3_list.html" target="main_self_frame">已取消订单</a></dd>
							<dd><a href="order_state4_list.html" target="main_self_frame">已发货订单</a></dd>
							<dd><a href="order_state5_list.html" target="main_self_frame">已结算订单</a></dd>
							<dd><a href="order_state6_list.html" target="main_self_frame">已拒收订单</a></dd>
							<dd><a href="order_state7_list.html" target="main_self_frame">已签收订单</a></dd>
							<dd><a href="order_state8_list.html" target="main_self_frame">已退货订单</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">统计报表</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;" target="main_self_frame">商品销售统计</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">财务管理</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;" target="main_self_frame">待结算流水</a></dd>
							<dd><a href="javascript:;" target="main_self_frame">已结算流水</a></dd>
							<dd><a href="javascript:;" target="main_self_frame">已返款流水</a></dd>
							<dd><a href="javascript:;" target="main_self_frame">财务返款单</a></dd>
							<dd><a href="javascript:;" target="main_self_frame">支付宝记录</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">用户管理</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;" target="main_self_frame">基本信息</a></dd>
							<dd><a href="javascript:;" target="main_self_frame">修改密码</a></dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>`
});