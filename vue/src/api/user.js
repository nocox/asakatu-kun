import ApiService from "./config";
 const UserService = {
    setUserImage: () => {
        return ApiService.get('/login_user');
    },
     getLogin(request) {
        return ApiService.post('/login',request);
     },
     whoAmI(){
        return ApiService.get('/login_user');
     },
     createUser(request) {
        // todo check this function
        return ApiService.post('/user_registration',
            JSON.stringify(request),
            {
                withCredentials: true,
                headers: {
                    'Content-Type': 'application/json'
                }
            });
     },
 };
export default UserService;
