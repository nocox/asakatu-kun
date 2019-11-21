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
     editDisplayName(request){
        return ApiService.put('/user/edit/display_name', request);
     },
     editImage(request){
        return ApiService.put('/user/edit/image', request);
     },

 };
export default UserService;
