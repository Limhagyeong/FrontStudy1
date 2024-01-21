<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
margin-top: 50px;
}
.row{
margin: 0px auto;
width: 100%
}
</style>
</head>
<body>
  <div class="app">
  <div class="container">
	<div class="row">
	<div class="col-md-3" v-for="vo in food">
					<div class="thumbnail">
						<a href="#"> 
						<img :src="vo.poster" style="width: 260px; height: 200px;">
							<div class="title">
							<p style="font-size: 9px;">{{vo.name}}</p>
							</div>
						</a>
					</div>
				</div>
	</div>
  </div>

  </div>

  <script>
  const App={
	data(){
		return {
			food:[]
		}
	},
	mounted(){
		// $(function(){})
		axios.get('../food/list.do').then(response=>{
			console.log(response.data)
			this.food=response.data
		})
	}
  }
  Vue.createApp(App).mount('.app') <!-- 가상메모리에 올려뒀다가 변경된 내용을 브라우저에 출력함 => 제어 영역 확인 잘하고 쓰기 (.app) -->
  </script>
</body>
</html>