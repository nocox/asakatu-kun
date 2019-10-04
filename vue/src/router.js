import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import store from './store';


Vue.use(Router);


const router = new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/sign_up',
      name: 'sign_up',
      component: () => import('./views/SignUp.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('./views/Login.vue')
    },
    {
      path: '/events',
      name: 'events',
      component: () => import('./views/Events.vue'),
      children: [
          {
            path: '',
            component: () => import('./components/AllEventList.vue'),
          },
          {
            path: 'joined',
            component: () => import('./components/JoinedEventList.vue'),
          }
        ]

    },
    {
      path: '/event/detail/:eventId',
      name: 'event',
      component: () => import('./views/Event.vue')
    },
    {
      path: '/event/create',
      name: 'event_create',
      component: () => import('./views/EventCreate.vue')
    },
    {
      path: '/demo',
      name: 'demo',
      component: () => import('./views/ComponentDemo.vue')
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: () => import('./views/MyPage.vue')
    },
    {
      path: '*',
      name:'others',
      redirect: '/'
    }
  ]
});

export default router;

//login check
router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/sign_up', '/', '/top'];
  const authRequired = !publicPages.includes(to.path);
  const isLoggedIn = store.state.isLogin;

  if (authRequired && !isLoggedIn) {
    return next('/login');
  }
  next();
});