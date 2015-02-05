<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布任务</title>
</head>
<body>

<table>
	<tr>
		<td colspan="3" align="center">发布任务</td> 
	</tr>
	<tr>
		<td>掌柜名称</td>
		<td><input type="text" value="taobao_account" readonly="readonly"></input></td>
		<td>如果没有绑定，请去绑定</td>
	</tr>
	<tr>
		<td>掌柜QQ</td>
		<td><input type="text" value="QQ " readonly="readonly"></input></td>
		<td>如果没有绑定，请去绑定</td>
	</tr>
	<tr>
		<td>商品标题</td>
		<td><input type="text" name="taobao_title"  ></input></td>
		<td>方便接手人准确找到您的商品</td>
	</tr>
	<tr>
		<td>商品地址</td>
		<td><input type="text" name="taobao_url"  ></input></td>
		<td>安全起见请尽量不要专刷同一款宝贝</td>
	</tr>
	<tr>
		<td>担保价格</td>
		<td><input type="text" name="taobao_url"  ></input></td>
		<td>担保价格 = 你淘宝的宝贝价格(或改价后价格) + 邮费的总价</td>
	</tr>
	<tr>
		<td>基本服务费</td>
		<td><input type="text" name="taobao_url"  ></input></td>
		<td>接手人完成基本任务获得的佣金</td>
	</tr>
	<tr>
		<td>追加服务费</td>
		<td><input type="text" name="taobao_url"  ></input></td>
		<td>接手人完成基本任务追加获得的佣金</td>
	</tr>
	 <tr>
		<td  colspan="3"> 
			<table>
				<thead>
					<tr>
						<td colspan="3"  align="center"><h2>基本任务</h2></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>好评要求时限</td>
						<td>
							<select>
								 <OPTION value=1>马上好评</OPTION> 
								  <OPTION value=2>30分钟后好评</OPTION> 
								  <OPTION selected value=3>1天后好评</OPTION> 
								  <OPTION value=4>2天后好评</OPTION> 
								  <OPTION value=5>3天后好评</OPTION> 
								  <OPTION value=6>4天后好评</OPTION> 
								  <OPTION value=7>5天后好评</OPTION> 
								  <OPTION value=8>6天后好评</OPTION> 
								  <OPTION value=9>7天后好评</OPTION>
							</select>
						</td>
						<td>如果您发布实物任务，建议选择2天到3天好评。不要选择立即确认，否则有可能被淘宝查出</td>
					</tr>
					<tr>
						<td>需要改价</td>
						<td>
							<input type="checkbox" >
						</td>
						<td>如果接手方拍下你宝贝后还需要修改宝贝价格，请勾上。（例如你改价后的商品价格+邮费=100元，担保价必须也是100元）</td>
					</tr>
					<tr>
						<td>需要旺旺聊天</td>
						<td>
							<input type="checkbox" checked="checked">
						</td>
						<td></td>
					</tr>
					<tr>
						<td>需要五星好评</td>
						<td>
							<input type="checkbox" checked="checked">
						</td>
						<td></td>
					</tr>
					<tr>
						<td>需要购物收藏</td>
						<td>
							<input type="checkbox" checked="checked">
						</td>
						<td></td>
					</tr>
					<tr>
						<td>购物前停留</td>
						<td>
							<input type="checkbox" checked="checked">
						</td>
						<td></td>
					</tr>
					<tr>
						<td>好评需要截图</td>
						<td>
							<input type="checkbox" checked="checked">
						</td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	 <tr>
		<td  colspan="3"> 
			<table>
				<thead>
					<tr>
						<td colspan="3" align="center"><h2>增值任务</h2></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>指定好评内容</td>
						<td>
							 需要<input type="checkbox" checked="checked">
							<textarea rows="5" cols="20"></textarea>
						</td>
						<td>最多100字,0.5元</td>
					</tr>
					<tr>
						<td>指定收获地址</td>
						<td>
							 需要<input type="checkbox" checked="checked">
							<textarea rows="5" cols="20"></textarea>
						</td>
						<td>最多100字,0.5元</td>
					</tr>
					<tr>
						<td>限制用户不重复接手</td>
						<td>
							需要<input type="checkbox" checked="checked">
						</td>
						<td>请输入接收人用户名,0.5个平台币</td>
					</tr>
					<tr>
						<td>指定接手人</td>
						<td>
							 需要<input type="checkbox" checked="checked">
							 <input></input>
						</td>
						<td>请输入接收人用户名,0.5个平台币</td> 
					</tr>
					<tr>
						<td>批量发布</td>
						<td>
							 需要<input type="checkbox" checked="checked">
							 <input></input>
						</td>
						<td>请输入批量发布条数,最多不超过10条，0.5个平台币</td> 
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
</table>
</body>
</html>