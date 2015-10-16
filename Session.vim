let SessionLoad = 1
let s:so_save = &so | let s:siso_save = &siso | set so=0 siso=0
let v:this_session=expand("<sfile>:p")
silent only
cd ~/Documents/School\ Senior\ Year/5526\ -\ NN/programming_assignment_2/rbf-network
if expand('%') == '' && !&modified && line('$') <= 1 && getline(1) == ''
  let s:wipebuf = bufnr('%')
endif
set shortmess=aoO
badd +4 ~/Documents/School\ Senior\ Year/5526\ -\ NN/programming_assignment_2/rbf-network/src/rbf_network/core.clj
badd +0 term://.//14119:/bin/bash
badd +0 term://.//14135:/bin/bash
badd +0 term://.//14151:/bin/bash
argglobal
silent! argdel *
edit ~/Documents/School\ Senior\ Year/5526\ -\ NN/programming_assignment_2/rbf-network/src/rbf_network/core.clj
set splitbelow splitright
wincmd _ | wincmd |
vsplit
wincmd _ | wincmd |
vsplit
2wincmd h
wincmd w
wincmd w
wincmd _ | wincmd |
split
wincmd _ | wincmd |
split
2wincmd k
wincmd w
wincmd w
set nosplitbelow
set nosplitright
wincmd t
set winheight=1 winwidth=1
exe 'vert 1resize ' . ((&columns * 31 + 101) / 203)
exe 'vert 2resize ' . ((&columns * 90 + 101) / 203)
exe '3resize ' . ((&lines * 13 + 21) / 42)
exe 'vert 3resize ' . ((&columns * 80 + 101) / 203)
exe '4resize ' . ((&lines * 13 + 21) / 42)
exe 'vert 4resize ' . ((&columns * 80 + 101) / 203)
exe '5resize ' . ((&lines * 12 + 21) / 42)
exe 'vert 5resize ' . ((&columns * 80 + 101) / 203)
argglobal
enew
file NERD_tree_1
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal nofen
wincmd w
argglobal
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 29 - ((28 * winheight(0) + 20) / 40)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
29
normal! 0
wincmd w
argglobal
edit term://.//14119:/bin/bash
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
let s:l = 13 - ((12 * winheight(0) + 6) / 13)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
13
normal! 0
wincmd w
argglobal
edit term://.//14135:/bin/bash
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
let s:l = 13 - ((12 * winheight(0) + 6) / 13)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
13
normal! 0
wincmd w
argglobal
edit term://.//14151:/bin/bash
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
let s:l = 12 - ((11 * winheight(0) + 6) / 12)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
12
normal! 0
wincmd w
2wincmd w
exe 'vert 1resize ' . ((&columns * 31 + 101) / 203)
exe 'vert 2resize ' . ((&columns * 90 + 101) / 203)
exe '3resize ' . ((&lines * 13 + 21) / 42)
exe 'vert 3resize ' . ((&columns * 80 + 101) / 203)
exe '4resize ' . ((&lines * 13 + 21) / 42)
exe 'vert 4resize ' . ((&columns * 80 + 101) / 203)
exe '5resize ' . ((&lines * 12 + 21) / 42)
exe 'vert 5resize ' . ((&columns * 80 + 101) / 203)
tabnext 1
if exists('s:wipebuf') && getbufvar(s:wipebuf, '&buftype') isnot# 'terminal'
  silent exe 'bwipe ' . s:wipebuf
endif
unlet! s:wipebuf
set winheight=1 winwidth=20 shortmess=filnxtToO
let s:sx = expand("<sfile>:p:r")."x.vim"
if file_readable(s:sx)
  exe "source " . fnameescape(s:sx)
endif
let &so = s:so_save | let &siso = s:siso_save
doautoall SessionLoadPost
unlet SessionLoad
" vim: set ft=vim :
