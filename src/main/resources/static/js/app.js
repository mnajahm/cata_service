var app=angular.module("MyApp",['ui.router']);

app.config(function($stateProvider,$urlRouterProvider){
	$stateProvider.state('home',{
		url:'/home',
		templateUrl: 'views/home.html',
		controller: 'HomeController'
	});
	$stateProvider.state('chercher',{
		url:'/chercher',
		templateUrl: 'views/chercher.html',
		controller: 'MyController'
	});
	$stateProvider.state('newProduit',{
		url:'/newProduit',
		templateUrl: "views/newProduit.html",
		controller: 'newProduitController'
	});

});

app.controller("HomeController", function() {
	
});


app.controller("newProduitController", function($scope,$http) {
	$scope.produit={designation:"",prix:0.0,quantite:0};
	$scope.mode=0;
	
	$scope.modeForm=function(){
		$scope.produit={designation:"",prix:0.0,quantite:0};
		$scope.mode=0;
	};
	$scope.saveProduit=function(){
		
		$http.post("http://localhost:8070/produits",$scope.produit)
		.success(function(data) {
			$scope.produit=data;
			$scope.mode=1;
		})
		.error(function(err) {
			console.log(err);
		});
		
	};
});

app.controller("MyController",function($scope,$http){
	$scope.pageProduits=null;
	$scope.motCle="";
	$scope.pageCourant=0;
	$scope.size=4;
	$scope.pages=[];
	$scope.chercherProduits=function(){
		$http.get("http://localhost:8070/chercherproduits?mc="+$scope.motCle+
				"&page="+$scope.pageCourant+
				"&size="+$scope.size)
		.success(function(data){
			$scope.pageProduits=data;
			$scope.pages=new Array(data.totalPages);
		})
		.error(function(err){
			console.log(err);
		});
		
	};
	
	$scope.gotoPage=function(p){
		$scope.pageCourant=p;
		$scope.chercherProduits();
	};
	$scope.getProduit=function(){
		$scope.pageCourant=0;
		$scope.chercherProduits();
	};
});