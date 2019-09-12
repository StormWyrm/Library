import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Home from '../components/Home'
import AppIndex from '../components/home/AppIndex'
import NoteIndex from '../components/note/NoteIndex'
import LibraryIndex from '../components/library/LibraryIndex'
import PersonalIndex from '../components/personal/PersonalIndex'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          meta: {
            requireAuth: true //需要认证
          }
        },
        {
          path: '/note',
          name: 'NoteIndex',
          component: NoteIndex,
          meta: {
            requireAuth: true //需要认证
          }
        },
        {
          path: '/library',
          name: 'LibraryIndex',
          component: LibraryIndex,
          meta: {
            requireAuth: true //需要认证
          }
        },
        {
          path: '/personal',
          name: 'PersonalIndex',
          component: PersonalIndex,
          meta: {
            requireAuth: true //需要认证
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },

  ]
})
