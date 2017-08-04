angular.module("lab03").controller("lab03Controller", function ($scope, $http, $state, $mdDialog) {
	$scope.app = "Lab03";
	$scope.series = [];
	$scope.watchlist = [];
	$scope.profile = [];
	$scope.exibicao = [];
	$scope.dialogSerie = {};
	$scope.inWatchlist = false;

	
	$scope.register = function(email, password, name){
	    console.log("register");
	    var url = "/register";
	    var data = {
	      name: name,
	      email: email,
	      password: password
	    };

	    $http.post(url, data).then(function (response) {
	      $scope.postResultMessage = "Concluído";
	      alert("Cadastro realizado.");

	    }, function (response) {
	      $scope.postResultMessage = "Algo deu errado";

	    });
	};
	  
	$scope.login = function(email, password){
	    $scope.profile = [];
	    $scope.exibicao = [];
	    $scope.watchlist = [];
		
	    var url = "/getin";
	    var data = {
	    		email: email,
	    		password: password

		};

	    $http.post(url, data).then(function (response) {
	    	$scope.postResultMessage = "Olá";

		    $scope.usuarioLogado = response.data.id;

		    $scope.seriesDoUsuario();
		    $scope.seriesDaWatchlist();
		    $state.go('main.home');
		    
		}, function (response) {
		});

	};
	
	$scope.logout = function(){
		   $state.go('main.login');
	}

	$scope.seriesDoUsuario = function() {
		var url = '/getSeries/' + $scope.usuarioLogado;

		$http.get(url).then(function (response) {
			var seriesPerfil = response.data;
		    for ( i = 0; i < profile.length; i++)   {
		    	$http.get("https://omdbapi.com/?i="+ seriesPerfil[i].imdbID +"&apikey=93330d3c&type=series").then(function(response) {
		    		$scope.profile.push(response.data);
		    	})

		    }
		})

	}

	$scope.seriesDaWatchlist = function() {
		var url = '/getSeriesWatchlist/' + $scope.usuarioLogado;

		$http.get(url).then(function (response) {
		    var seriesWatchlist = response.data;
		    for ( i = 0; i < watchlist.length; i++)   {
		      $http.get("https://omdbapi.com/?i="+ seriesWatchlist[i].imdbID +"&apikey=93330d3c&type=series").then(function(response) {
		        $scope.watchlist.push(response.data);
		      })

		    }

		})
	}
	
	$scope.buscarSerie = function (serie) {
		$http.get("https://omdbapi.com/?s="+ serie +"&apikey=93330d3c&type=series").then(function (response) {

			if(response.data.Response == "True") {
				$scope.series = response.data.Search;
				$scope.exibicao = $scope.series;
			} else {
				alert("A série não foi encontrada");
			};
			delete $scope.serie;
		});
	}

	$scope.addSerieWatchlist = function (serie) {
		if($scope.serieExists(serie, $scope.watchlist)) {
			alert('"'+serie.Title+'" já está na sua Watchlist');
		} else if ($scope.serieExists(serie, $scope.profile)) {
			alert('"'+serie.Title+'" já está no seu perfil.')
		} else {
			$scope.watchlist.push(serie);
			$scope.addWatchlist(serie);
			alert('"'+serie.Title+'" foi adicionada à sua Watchlist')
		}
	}
	
	$scope.addWatchlist = function(serie) {
		  $scope.inWatchlist = true;
		  var data = {
		    idUsuario: $scope.usuarioLogado,
		    imdbId: serie.imdbID,
		    nome: serie.Title,
		    inWatchlist: $scope.inWatchlist,
		    avaliacao: 0,
		    ultimoEpisodio: 0
		  };
		  var url = "/saveWatchlist";
		  $http.post(url, data).then(function (response) {
		  }, function (response) {
		  });
		}

	$scope.addSerieProfile = function (serie) {
		if ($scope.serieExists(serie, $scope.profile)) {
			alert("A série selecionada já está no seu perfil.")
		} else {
			if ($scope.serieExists(serie, $scope.watchlist)) {
				$scope.profile.push(serie);
				$scope.inWatchlist = false;
				$scope.add(serie);
				$scope.removeSerieWatchlist(serie);
				alert('"'+serie.Title+'" foi movida da sua watchlist para o seu perfil.')
			} else {
				$scope.profile.push(serie);
				$scope.add(serie);
				alert('"'+serie.Title+'" foi adicionada ao seu perfil')
			}

		}
	}
	
	$scope.add = function(serie) {
		$scope.inWatchlist = false;
		var data = {
		    idUsuario: $scope.usuarioLogado,
		    imdbId: serie.imdbID,
		    nome: serie.Title,
		    inWatchlist: $scope.inWatchlist,
		    avaliacao: 0,
		    ultimoEpisodio: 0
		  };
		  var url = "/save";
		  $http.post(url, data).then(function (response) {
		  }, function (response) {
		  });
		}

	
	$scope.removeDaWatchlist = function (serie) {
		var indexSerieWatchlist = $scope.watchlist.indexOf(serie);
		
		if (indexSerieWatchlist > -1) {
			$scope.watchlist.splice(indexSerieWatchlist, 1);
		    $scope.watchlist.splice(indexSerieWatchlist, 1);
		  }
		  $scope.inWatchlist = false;
	}

	$scope.removeSerieWatchlist = function(serie){
	  var url = "/removeWatchlist/" + $scope.usuarioLogado;
	  $http.post(url, serie.imdbID).then(function(response){
	  }, function(response){
	  });
	};	
	
	$scope.removeSerieProfile = function (serie) {
		if (confirm('Tem certeza que deseja remover "'+serie.Title+'"?') === true) {
			var indexSerieProfile = $scope.profile.indexOf(serie);
			if (indexSerieProfile > -1) {
				$scope.profile.splice(indexSerieProfile, 1);
				$scope.remove(serie);
				alert('"'+serie.Title+'" foi removida do seu perfil.')
			}
		}
	};

	$scope.remove = function(serie){
		  var url = "/remove/" + $scope.usuarioLogado;
		  $http.post(url, serie.imdbID).then(function(response){

		  }, function(response){
		  });
		};

	$scope.serieExists = function (serie, list) {
		return (list.indexOf(serie) != -1);
	};
	
	$scope.verInfo = function (ev, serie) {
		$http.get("https://omdbapi.com/?i="+ serie.imdbID +"&apikey=93330d3c&type=series").then(function (response) {
			$scope.serieDialog = response.data;
			
			$mdDialog.show({
				controller: DialogController,
				templateUrl: 'seriesInfo.html',
				parent: angular.element(document.body),
				targetEvent: ev,
				clickOutsideToClose:true,
				locals: {
					serieDialog: $scope.serieDialog
				}
			});
		});

	}

	function DialogController($scope, $mdDialog, serieDialog) {
		$scope.serie = serieDialog;

		$scope.hide = function() {
			$mdDialog.hide();
		};

		$scope.cancel = function() {
			$mdDialog.cancel();
		};

		$scope.answer = function(answer) {
			$mdDialog.hide(answer);
		};
	}
});