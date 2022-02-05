package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // mac 단축키 command, shift, enter하면 자동완성 시 세미콜론 추가됨
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
