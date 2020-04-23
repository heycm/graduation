import Part from './Part.vue';
// 多组件全局注册
import Part2 from './Part.vue';

export default (Vue) => {
    Vue.component('part', Part)
    // 多组件全局注册
    Vue.component('Part', Part2)
}