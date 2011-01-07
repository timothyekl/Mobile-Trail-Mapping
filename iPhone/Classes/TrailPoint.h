#import <Foundation/Foundation.h>
#import "InterestPoint.h"
#import "Trail.h"

@interface TrailPoint : InterestPoint {
    
    Trail *_trail;
    NSMutableSet *_connections;
    NSMutableArray *_unresolvableLinks;
    bool _hasUnresolvedLinks;
}

@property (nonatomic, retain) Trail *trail;
@property (nonatomic, retain) NSMutableSet *connections;
@property (nonatomic, retain) NSMutableArray *unresolvableLinks;

@end
