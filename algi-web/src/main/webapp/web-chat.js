window.onload = function(){
	switchLang();
}


const CN = "zh";
const EN = "en";
const CN_ICON = "icon/icon_cn.png";
const EN_ICON = "icon/icon_en.png";
const CN_SEND = "发 送";
const EN_SEND = "SEND";

const DEFAULT_LANG = CN;
const DEFAULT_ICON = CN_ICON;
const DEFAULT_SEND = CN_SEND;

const LEFT_ICON = "icon/icon002.jpeg";
const RIGHT_ICON = "icon/icon217.jpg";


if(!!window.EventSource){
	var id = crypto.randomUUID();
	var source = new EventSource(`/algi-sensors/chat/subscribe?id=${id}`);

	source.onmessage = function(e){
		appendLeft(e.data);
	};

	source.onopen = function(e){
	};

	source.onerror = function(e){
	};

	source.addEventListener('closeSse', function(e){
		source.close();
		console.log(e);
	}, false);

	window.onbeforeunload = function(){
		closeSse(id);
	};
}else{
	alert("浏览器不支持EventSource / Browser doesn't support EventSource.");
}

function closeSse(id){
	source.close();
	const httpRequest = new XMLHttpRequest();
	httpRequest.open('GET', `/algi-sensors/sse/closeSse/?id=${id}`, true);
	httpRequest.send();
	console.log("closeSse");
}


function send(){
	let text = document.querySelector('#textarea').value;
	if(!text){
		alert('请输入内容 / Please input content.');
		return;
	}
	appendRight(text);

	var lang = getLang();
	postJson('/algi-sensors/chat/chat/', {"id":id, "text":text, "lang":lang});
}

function postJson(url, data){
	var httpRequest = new XMLHttpRequest();
	httpRequest.open('POST', url, true);
	httpRequest.setRequestHeader("Content-type","application/json; charset=utf-8");
	httpRequest.send(JSON.stringify(data));
	httpRequest.onreadystatechange = function(){
		if(httpRequest.readyState == 4 && httpRequest.status == 200){
			var json = httpRequest.responseText;
			console.log(json);
		}
	};
}



function appendLeft(data){
	item = document.createElement('div');
	item.className = 'item item-left';
	item.innerHTML = `<div class="avatar"><img src="${LEFT_ICON}" /></div>
		<div class="bubble bubble-left">${lineFeed(data)}</div>`;
	appendItem(item);
}

function appendRight(data){
	item = document.createElement('div');
	item.className = 'item item-right';
	item.innerHTML = `<div class="bubble bubble-right">${lineFeed(data)}</div>
		<div class="avatar"><img src="${RIGHT_ICON}" /></div>`;
	appendItem(item);
}

function appendItem(item){
	document.querySelector('.content').appendChild(item);
	document.querySelector('#textarea').value = '';
	document.querySelector('#textarea').focus();
	contentScrollToBottom();
}

function contentScrollToBottom(){
	height = document.querySelector('.content').scrollHeight;
	document.querySelector(".content").scrollTop = height;
}

function lineFeed(data){
	if(data == null){
		return null;
	}

	const dataArray = data.split('\n');
	var text = "";
	var i;
	for(i = 0; i < dataArray.length; i++){
		text += dataArray[i];
		if(i < dataArray.length -1){
			text += "<br/>";
		}
	}
	return text;
}


function switchLang(){
	var sendBtn = document.getElementById('send-btn');
	var switchBtn = document.getElementById('switch-lang-btn');
	sendBtn.disabled = true;
	switchBtn.disabled = true;

	if(switchBtn.value == "" || switchBtn.value == null){
		sendBtn.innerText = DEFAULT_SEND;
		switchBtn.value = DEFAULT_LANG;
		switchBtn.innerHTML = `<img src="${DEFAULT_ICON}" />`;
	}else if(switchBtn.value == CN){
		sendBtn.innerText = EN_SEND;
		switchBtn.value = EN;
		switchBtn.innerHTML = `<img src="${EN_ICON}" />`;
	}else if(switchBtn.value == EN){
		sendBtn.innerText = CN_SEND;
		switchBtn.value = CN;
		switchBtn.innerHTML = `<img src="${CN_ICON}" />`;
	}
	showTime();
	prompt();
	greeting();
	contentScrollToBottom();

	switchBtn.disabled = false;
	sendBtn.disabled = false;
}

function getLang(){
	var switchBtn = document.getElementById('switch-lang-btn');
	if(switchBtn == null){
		return DEFAULT_LANG;
	}else if(switchBtn.value == "" || switchBtn.value == null){
		switchLang();
	}
	return switchBtn.value;
}

function showTime(){
	var lang = getLang();
	var locale;
	if(lang == CN){
		locale = "ch-cn";
	}else if(lang == EN){
		locale = "en-us";
	}

	var datetime = new Date().toLocaleDateString(locale, 
		{year:"numeric", month:"short", day:"numeric", hour:"numeric", minute:"numeric"});
	item = document.createElement('div');
	item.className = 'item item-center';
	item.innerHTML = `<span>${datetime}</span>`;
	document.querySelector('.content').appendChild(item);
}

function prompt(){
	var lang = getLang();
	var greet;
	if(lang == CN){
		greet = "您已添加了algi-Holiday，现在可以开始聊天了。";
	}else if(lang == EN){
		greet = "You have added Algi-Holiday, and now it's ready for chatting.";
	}
	item = document.createElement('div');
	item.className = 'item item-center';
	item.innerHTML = `<span>${greet}</span>`;
	document.querySelector('.content').appendChild(item);
}

function greeting(){
	var lang = getLang();
	$.ajax({
		url:`/algi-sensors/chat/greeting/?lang=${lang}`, 
		type:"get", 
		success:function(data){
			appendLeft(data.text);
		}, 
		error:function(){
			alert("请求失败 / Greeting fail.");
			console.log(data);
		}
	});
}
