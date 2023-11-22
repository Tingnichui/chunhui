<template>
	<el-dialog v-model="isShow" :close-on-click-modal="false" title="请输入验证码" width="300px" center>
		<view style="display: flex;justify-content: space-between;align-items: center;padding: 0px 15px;">
			<text @click="createCode()" style="width: 100px;height: 30px;background-color: #D8B7E3;font-size: 20px;color: #0066CC;text-align: center;cursor: pointer;user-select: none;" v-text="value"></text>
			<el-input style="width: 100px;height: 30px;" v-model="inputValue" placeholder="输入验证码"></el-input>
		</view>
		<el-button type="primary" style="width: 100%;margin-top: 20px;align-items: center;" @click="checkCodeValue">确认</el-button>
	</el-dialog>
</template>

<script>
	import { reactive, toRefs } from 'vue'
	import { ElMessage } from 'element-plus'
	export default {
		name: 'Captcha',
		props: {
			// 这里业务方法，验证码正确后调用do方法
			do: Function
		},
		setup(props) {
			const captchaInfo = reactive({
				isShow: false,
				value: '',
				inputValue: ''
			})
			const open = () => {
				captchaInfo.isShow = true;
				createCode();
			}
			const createCode = (length=4) => {
				captchaInfo.value = "";
				let codeLength = parseInt(length); //验证码的长度
				let codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
				'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); 
				for (let i = 0; i < codeLength; i++) {
					captchaInfo.value += codeChars[Math.floor(Math.random() * 62)];
				}
			}
			const checkCodeValue = () => {
				if (captchaInfo.value.length != captchaInfo.inputValue.length || captchaInfo.value.toUpperCase() != captchaInfo.inputValue.toUpperCase()) {
					createCode();
					ElMessage.error({
						showClose: true,
						message:'验证码错误'
					});
				} else {
					props.do();
					captchaInfo.isShow=false;
				}
				captchaInfo.inputValue = '';
			}
			return {
				...toRefs(captchaInfo),
				createCode,
				checkCodeValue,
				open
			}
		}
	}
</script>