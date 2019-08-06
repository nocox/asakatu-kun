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
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
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
      component: () => import('./views/Events.vue')
    },
    {
      path: '/event',
      name: 'event',
      component: () => import('./views/Event.vue')
    },
    {
      path: '/event/create',
      name: 'event_create',
      component: () => import('./views/EventCreate.vue')
    }
  ]
})

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