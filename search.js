var btn = document.getElementById('search')

const { login_cellphone, user_cloud } = require('NeteaseCloudMusicApi')
async function search() {
  try {
    const result = await login_cellphone({
      phone: 'xxx',
      password: 'xxx',
    })
    console.log(result)
    const result2 = await user_cloud({
      cookie: result.body.cookie, // 凭证
    })
    console.log(result2.body)
  } catch (error) {
    console.log(error)
  }
}