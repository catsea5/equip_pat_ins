import defaultSettings from '@/settings'

const title = defaultSettings.title || '设备巡检管理'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
