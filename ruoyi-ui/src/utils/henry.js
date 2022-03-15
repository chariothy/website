
/** 从磁力地址中解析出描述文字 */
export function parseMagnetDesc(magnetUrl) {
  let regMagnet = /dn=(?:\[.+\])?([^&]+)/ig
  let match = magnetUrl.match(regMagnet)
  if(match) {
    return RegExp.$1
  }
  return '磁力地址'
}
