import $ from 'jquery'

const ModuleUser = {
    state: {
        id: "",
        username: "",
        photo: "",
        token: "",
        is_login: false,
    },
    getters: {
    },
    mutations: {
        updateUser(state,user) {
            state.id = user.id;
            state.username = user.username;
            state.photo = user.photo;
            state.is_login = user.is_login;
        },
        updateToken(state,token){
            state.token = token;
        },
        logout(state) {
            state.id = '';
            state.username = '';
            state.photo = '';
            state.token = '';
            state.is_login = false;
        }
    },
    actions: {
        login(context,data) {
            $.ajax({
                url: 'http://localhost:3000/user/account/token/',
                type: 'post',
                data: {
                    username: data.username,
                    password: data.password,
                },
                success(resp) {
                    localStorage.setItem("jwt_token",resp.token);
                    context.commit("updateToken",resp.token);
                    data.success();
                },
                error() {
                    data.error();
                }
            })
        },
        getInfo(context,data) {
            $.ajax({
                url: "http://localhost:3000/user/account/info/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + context.state.token,
                },
                success(resp){
                    if(resp.error_message==="success"){
                        context.commit("updateUser",{
                            ...resp,
                            is_login: true,
                        })
                        data.success();
                    }
                }
            })
        },
        logout(context){
            localStorage.removeItem("jwt_token");
            context.commit("logout");
        }
    },
    modules: {
    }
}

export default ModuleUser;
