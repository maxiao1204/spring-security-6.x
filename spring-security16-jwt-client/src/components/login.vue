<script setup lang="ts">

import {ref} from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

import axios from "axios";
const username = ref('');
const password = ref('');

async function doLogin(){
  var options = {  // 设置axios的参数
    url: '/api/login',
    data: {username:username.value,password:password.value},
    method: 'post',
    headers: {'Content-Type':'application/x-www-form-urlencoded'}
  }

    const ret = await axios(options);

    console.log(ret.data.token);

  var options2 = {  // 设置axios的参数
    url: '/api/index',
    method: 'get',
    headers: {token:ret.data.token+"fdsafdsa"}
  }

      const ret2 = await axios(options2);

    console.log(ret2);

}


</script>

<template>
<h1 style="background-color: chocolate">欢迎登录</h1>

  用户名：<input type="text" v-model="username"><br/>
  密码：<input type="text" v-model="password"><br/>
    <button @click="doLogin">登录</button>

</template>

<style scoped>

</style>
