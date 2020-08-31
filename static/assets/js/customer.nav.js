Vue.component("leftnav-cus",{
	template:`<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed">
						<a class="" href="javascript:;">系统首页</a>
					</li>
					<li class="layui-nav-item">
						<a class="" href="javascript:;">商品管理</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;">商品信息</a></dd>
							<dd><a href="javascript:;">增加商品</a></dd>
							<dd><a href="javascript:;">增加文案</a></dd>
							<dd><a href="javascript:;">增加微信文案</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">订单管理</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;">全部订单</a></dd>
							<dd><a href="javascript:;">已下单订单</a></dd>
							<dd><a href="javascript:;">待跟进订单</a></dd>
							<dd><a href="javascript:;">待发货订单</a></dd>
							<dd><a href="javascript:;">已取消订单</a></dd>
							<dd><a href="javascript:;">已发货订单</a></dd>
							<dd><a href="javascript:;">已结算订单</a></dd>
							<dd><a href="javascript:;">已拒收订单</a></dd>
							<dd><a href="javascript:;">已签收订单</a></dd>
							<dd><a href="javascript:;">已退货订单</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">统计报表</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;">商品销售统计</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">财务管理</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;">待结算流水</a></dd>
							<dd><a href="javascript:;">已结算流水</a></dd>
							<dd><a href="javascript:;">已返款流水</a></dd>
							<dd><a href="javascript:;">财务返款单</a></dd>
							<dd><a href="javascript:;">支付宝记录</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">用户管理</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;">基本信息</a></dd>
							<dd><a href="javascript:;">修改密码</a></dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>`
});