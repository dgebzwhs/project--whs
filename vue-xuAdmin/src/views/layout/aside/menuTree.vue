<template>
    <div>
      <template v-for="(child,index) in menuData">
        <el-submenu v-if="child.children.length > 0" :index="child.path">
          <template slot="title">
            <i :class="child.iconCls?child.iconCls:[fa,fa-file]"></i>
            <span slot="title">{{ $t(`routeName.${child.name}`) }}</span>
          </template>
          <menu-tree :menuData="child.children"></menu-tree>
        </el-submenu>
        <el-menu-item v-else-if="!child.hidden" :index="child.path">
          <i :class="child.iconCls?child.iconCls:[fa,fa-file]"></i>
          <span slot="title">{{ $t(`routeName.${child.name}`) }}</span>
        </el-menu-item>
      </template>
    </div>
</template>

<script>
export default {
  name: 'menuTree',
  props: ['menuData']
}
</script>

<style scoped>

</style>
